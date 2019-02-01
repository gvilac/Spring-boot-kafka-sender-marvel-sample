package com.guillem.configuration;

import com.guillem.model.RequestCreators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by guillem on 25/01/2019.
 */
@Configuration
@PropertySource("classpath:/com/guillem/configuration/MarvelClientConfig.properties")
public class MarvelClientConfig {

    private static final Logger log = LoggerFactory.getLogger(MarvelClientConfig.class);

    private final static String URL_CREATORS = "https://gateway.marvel.com/v1/public/creators?#params#ts=%s&apikey=%s&hash=%s";

    private final static String FIRSTNAME = "firstName=";
    private final static String MODIFIEDSINCE = "modifiedSince=";
    private final static String ORDERBY = "orderBy=";
    private final static String COMICS = "comics=";
    private final static String EVENTS = "events=";
    private final static String STORIES = "stories=";
    private final static String SERIES = "series=";
    private final static String AND = "&";
    private final static String PARAMS = "#params#";

    private Environment env;

    public MarvelClientConfig(Environment env) {
        this.env = env;
    }

    public String getMarvelConfiguredURL(RequestCreators requestCreators) {
        Long timestamp = System.currentTimeMillis();
        String url = String.format(URL_CREATORS, timestamp, env.getProperty("publicKey"), getHashAutentication(timestamp));
        String urlParameters = getUrlParameters(requestCreators);
        url = url.replaceFirst(PARAMS, urlParameters);
        return url;
    }

    private String getUrlParameters(RequestCreators requestCreators) {
        String url = "";
        if (requestCreators.getFirstName() != null) {
            url = url + FIRSTNAME + requestCreators.getFirstName() + AND;
        }
        if (requestCreators.getModifiedSince() != null) {
            url = url + MODIFIEDSINCE + requestCreators.getModifiedSince() + AND;
        }
        if (requestCreators.getOrderBy() != null) {
            url = url + ORDERBY + requestCreators.getOrderBy() + AND;
        }
        if (requestCreators.getComics() != null) {
            url = url + COMICS + requestCreators.getComics() + AND;
        }
        if (requestCreators.getSeries() != null) {
            url = url + EVENTS + requestCreators.getSeries() + AND;
        }
        if (requestCreators.getStories() != null) {
            url = url + STORIES + requestCreators.getStories() + AND;
        }
        if (requestCreators.getEvents() != null) {
            url = url + SERIES + requestCreators.getEvents() + AND;
        }
        return url;
    }

    private String getHashAutentication(Long timestamp){
        String toDigest = timestamp + env.getProperty("privateKey") + env.getProperty("publicKey");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(toDigest.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            log.error("Error during hash calculation. " + e);
            return "";
        }
    }

}
