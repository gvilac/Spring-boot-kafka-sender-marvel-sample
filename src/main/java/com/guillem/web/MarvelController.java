package com.guillem.web;

import com.guillem.exception.NotFoundInDatabaseException;
import com.guillem.model.Creator;
import com.guillem.model.RequestCreators;
import com.guillem.service.MarvelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by guillem on 25/01/2019.
 */
@RestController
public class MarvelController {

    private final MarvelService marvelService;

    @Autowired
    public MarvelController(MarvelService marvelService) {
        this.marvelService = marvelService;
    }

    @RequestMapping("/creators")
    public List<Creator> findAll(@RequestParam(value = "firstName", required=false) String firstName,
                                 @RequestParam(value = "modifiedSince", required=false) String modifiedSince,
                                 @RequestParam(value = "orderBy", required=false) String orderBy,
                                 @RequestParam(value = "comics", required=false) String comics,
                                 @RequestParam(value = "series", required=false) String series,
                                 @RequestParam(value = "stories", required=false) String stories,
                                 @RequestParam(value = "events", required=false) String events
                                 ) throws IOException {
        RequestCreators requestCreators = new RequestCreators()
                .withFirstName(firstName)
                .withModifiedSince(modifiedSince)
                .withOrderBy(orderBy)
                .withComics(comics)
                .withSeries(stories)
                .withEvents(events)
                .withSeries(series).build();

        return marvelService.getCreators(requestCreators);
    }

    /*@RequestMapping("/creator/{id}")
    public Creator findById(@PathVariable String id) throws NotFoundInDatabaseException {
        return marvelService.getCreator(id);
    }*/

    @RequestMapping(method = RequestMethod.POST, value = "/person")
    public Creator add(@RequestBody Creator p) {
        //p.setId((persons.size()+1));
        //persons.add(p);
        //return p;
        return null;
    }


}
