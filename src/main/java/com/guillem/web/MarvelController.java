package com.guillem.web;

import com.guillem.exception.MapperException;
import com.guillem.model.Creator;
import com.guillem.model.RequestCreators;
import com.guillem.service.KafkaSender;
import com.guillem.service.MarvelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by guillem on 25/01/2019.
 */
@RestController
public class MarvelController {

    private final MarvelService marvelService;
    private final KafkaSender kafkaSender;

    @Autowired
    public MarvelController(MarvelService marvelService, KafkaSender kafkaSender) {
        this.marvelService = marvelService;
        this.kafkaSender = kafkaSender;
    }

    @ApiOperation(value = "Returns list of creators and sends through Kafka")
    @GetMapping("/creators")
    public List<Creator> findAll(@RequestParam(value = "firstName", required = false) String firstName,
                                 @RequestParam(value = "modifiedSince", required = false) String modifiedSince,
                                 @RequestParam(value = "orderBy", required = false) String orderBy,
                                 @RequestParam(value = "comics", required = false) String comics,
                                 @RequestParam(value = "series", required = false) String series,
                                 @RequestParam(value = "stories", required = false) String stories,
                                 @RequestParam(value = "events", required = false) String events
    ) throws MapperException {
        RequestCreators requestCreators = new RequestCreators()
                .withFirstName(firstName)
                .withModifiedSince(modifiedSince)
                .withOrderBy(orderBy)
                .withComics(comics)
                .withSeries(stories)
                .withEvents(events)
                .withSeries(series).build();

        List<Creator> creators = marvelService.getCreators(requestCreators);
        creators.forEach(kafkaSender::sendCreator);
        return creators;
    }

    @ApiOperation(value = "Sends a custom creator through Kafka")
    @PostMapping("/creator")
    public void addCreator(Creator creator) {
        kafkaSender.sendCreator(creator);
    }


}
