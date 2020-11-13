package com.example.marvelandroid.model;

import java.util.ArrayList;

public class ModelCharacter {
    private String id;
    private String name;
    private String description;
    private String image;
    private ArrayList<ModelComic> comics;
    private ArrayList<ModelSerie> series;
    private ArrayList<ModelStorie> stories;
    private ArrayList<ModelEvent> events;
    private String resourceURI;

    public ModelCharacter(String name, String resourceURI) {
        this.name = name;
        this.resourceURI = resourceURI;
    }

    public ModelCharacter(String id, String name, String description, String image, ArrayList<ModelComic> comics, ArrayList<ModelSerie> series, ArrayList<ModelStorie> stories, ArrayList<ModelEvent> events, String resourceURI) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.comics = comics;
        this.series = series;
        this.stories = stories;
        this.events = events;
        this.resourceURI = resourceURI;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<ModelComic> getComics() {
        return comics;
    }

    public void setComics(ArrayList<ModelComic> comics) {
        this.comics = comics;
    }

    public ArrayList<ModelSerie> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<ModelSerie> series) {
        this.series = series;
    }

    public ArrayList<ModelStorie> getStories() {
        return stories;
    }

    public void setStories(ArrayList<ModelStorie> stories) {
        this.stories = stories;
    }

    public ArrayList<ModelEvent> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<ModelEvent> events) {
        this.events = events;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }
}
