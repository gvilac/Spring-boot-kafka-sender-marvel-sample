package com.guillem.service;

import com.guillem.configuration.MarvelClientConfig;
import com.guillem.kafka.KafkaSender;
import com.guillem.mapper.CreatorMapper;
import com.guillem.model.Creator;
import com.guillem.model.RequestCreators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;


@Service
public class MarvelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarvelService.class);

    private final MarvelClientConfig marvelClientConfig;
    private final CreatorMapper creatorMapper;
    private final KafkaSender kafkaSender;

    @Autowired
    public MarvelService(MarvelClientConfig marvelClientConfig, CreatorMapper creatorMapper, KafkaSender kafkaSender) {
        this.marvelClientConfig = marvelClientConfig;
        this.creatorMapper = creatorMapper;
        this.kafkaSender = kafkaSender;
    }

    public List<Creator> getCreators(RequestCreators requestCreators) throws IOException {
        LOGGER.info("Starting service...");

        ResponseEntity<String> response = new RestTemplate().getForEntity(marvelClientConfig.getMarvelConfiguredURL(requestCreators), String.class);
        List<Creator> creators = creatorMapper.mapCreators(response);
        //creatorRepository.saveAll(creators); send to kafka
        for (Creator creator : creators) {
            kafkaSender.sendCreator(creator);
        }
        return creators;
    }

    /*public Creator getCreator(String id) throws NotFoundInDatabaseException {
        return creatorRepository.findById(id).orElseThrow(() -> new NotFoundInDatabaseException("Could not found creator " + id));
    }*/

}
