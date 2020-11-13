package com.example.marvelandroid.model;

import java.util.ArrayList;

public class ModelStorie {
    private String id;
    private String name;
    private String description;
    private ArrayList<ModelCreator> creators;
    private ArrayList<ModelCharacter> characters;
    private ArrayList<ModelSerie> series;
    private ArrayList<ModelComic> comics;
    private ArrayList<ModelEvent> events;
    private String resourceURI;

    public ModelStorie(String name, String resourceURI) {
        this.name = name;
        this.resourceURI = resourceURI;
    }

    public ModelStorie(String id, String name, String description, ArrayList<ModelCreator> creators, ArrayList<ModelCharacter> characters, ArrayList<ModelSerie> series, ArrayList<ModelComic> comics, ArrayList<ModelEvent> events, String resourceURI) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creators = creators;
        this.characters = characters;
        this.series = series;
        this.comics = comics;
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

    public ArrayList<ModelCreator> getCreators() {
        return creators;
    }

    public void setCreators(ArrayList<ModelCreator> creators) {
        this.creators = creators;
    }

    public ArrayList<ModelCharacter> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<ModelCharacter> characters) {
        this.characters = characters;
    }

    public ArrayList<ModelSerie> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<ModelSerie> series) {
        this.series = series;
    }

    public ArrayList<ModelComic> getComics() {
        return comics;
    }

    public void setComics(ArrayList<ModelComic> comics) {
        this.comics = comics;
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
