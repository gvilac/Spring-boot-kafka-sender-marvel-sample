package com.guillem.model;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by guillem on 28/01/2019.
 */
public class RequestCreators {

    private String firstName;
    private String modifiedSince;
    private String orderBy;
    private String comics;
    private String series;
    private String stories;
    private String events;

    public String getFirstName() {
        return firstName;
    }

    public String getModifiedSince() {
        return modifiedSince;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getComics() {
        return comics;
    }

    public String getSeries() {
        return series;
    }

    public String getStories() {
        return stories;
    }

    public String getEvents() {
        return events;
    }

    public RequestCreators withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public RequestCreators withModifiedSince(String modifiedSince) {
        this.modifiedSince = modifiedSince;
        return this;
    }

    public RequestCreators withOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public RequestCreators withComics(String comics) {
        this.comics = comics;
        return this;
    }

    public RequestCreators withSeries(String series) {
        this.series = series;
        return this;
    }

    public RequestCreators withStories(String stories) {
        this.stories = stories;
        return this;
    }

    public RequestCreators withEvents(String events) {
        this.events = events;
        return this;
    }

    public RequestCreators build() {
        RequestCreators requestCreators = new RequestCreators();
        requestCreators.firstName = this.firstName;
        requestCreators.modifiedSince = this.modifiedSince;
        requestCreators.orderBy = this.orderBy;
        requestCreators.comics = this.comics;
        requestCreators.series = this.series;
        requestCreators.stories = this.stories;
        requestCreators.events = this.events;
        return requestCreators;
    }

}
