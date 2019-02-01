package com.guillem.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.guillem.model.Creator;
import com.guillem.model.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillem on 28/01/2019.
 */
@Component
public class CreatorMapper {

    public List<Creator> mapCreators(ResponseEntity<String> response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode data = root.path("data");

        List<Creator> creators = new ArrayList<Creator>();
        ArrayNode array = (ArrayNode) data.path("results");
        for (JsonNode json : array) {
            Creator creator = new Creator();
            creator.setCreatorId(json.get("id").toString());
            creator.setFirstName(json.get("firstName").textValue());
            creator.setMiddleName(json.get("middleName").textValue());
            creator.setLastName(json.get("lastName").textValue());
            creator.setSuffix(json.get("suffix").textValue());
            creator.setFullName(json.get("fullName").textValue());
            creator.setModified(json.get("modified").textValue());

            creator.setComics(getResultFromJsonNode(json.get("comics")));
            creator.setSeries(getResultFromJsonNode(json.get("series")));
            creator.setStories(getResultFromJsonNode(json.get("stories")));
            creator.setEvents(getResultFromJsonNode(json.get("events")));

            creators.add(creator);
        }
        return creators;
    }

    private List<Result> getResultFromJsonNode(JsonNode jsonNode) {
        List<Result> results = new ArrayList<>();
        if (jsonNode.get("items") == null) {
            return results;
        }
        for (JsonNode node : jsonNode.get("items")) {
            String url = node.get("resourceURI").asText();
            String id = url.substring(url.lastIndexOf("/") + 1);
            results.add(new Result(id, node.get("name").asText()));
        }
        return results;
    }
}
