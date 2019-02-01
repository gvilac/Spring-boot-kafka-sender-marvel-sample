package com.guillem.model;

import java.util.List;

/**
 * Created by guillem on 23/01/2019.
 */
public class Creator {

    private String creatorId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private String fullName;
    private String modified;
    private List<Result> comics;
    private List<Result> series;
    private List<Result> stories;
    private List<Result> events;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public List<Result> getComics() {
        return comics;
    }

    public void setComics(List<Result> comics) {
        this.comics = comics;
    }

    public List<Result> getSeries() {
        return series;
    }

    public void setSeries(List<Result> series) {
        this.series = series;
    }

    public List<Result> getStories() {
        return stories;
    }

    public void setStories(List<Result> stories) {
        this.stories = stories;
    }

    public List<Result> getEvents() {
        return events;
    }

    public void setEvents(List<Result> events) {
        this.events = events;
    }
}
