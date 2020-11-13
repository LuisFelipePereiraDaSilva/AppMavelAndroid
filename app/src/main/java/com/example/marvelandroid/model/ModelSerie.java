package com.example.marvelandroid.model;

import java.util.ArrayList;

public class ModelSerie {
    private String id;
    private String name;
    private String description;
    private String startYear;
    private String endYear;
    private String image;
    private ArrayList<ModelCreator> creators;
    private ArrayList<ModelCharacter> characters;
    private ArrayList<ModelStorie> stories;
    private ArrayList<ModelComic> comics;
    private ArrayList<ModelEvent> events;
    private String resourceURI;

    public ModelSerie(String name, String resourceURI) {
        this.name = name;
        this.resourceURI = resourceURI;
    }

    public ModelSerie(String id, String name, String description, String startYear, String endYear, String image, ArrayList<ModelCreator> creators, ArrayList<ModelCharacter> characters, ArrayList<ModelStorie> stories, ArrayList<ModelComic> comics, ArrayList<ModelEvent> events, String resourceURI) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startYear = startYear;
        this.endYear = endYear;
        this.image = image;
        this.creators = creators;
        this.characters = characters;
        this.stories = stories;
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

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public ArrayList<ModelStorie> getStories() {
        return stories;
    }

    public void setStories(ArrayList<ModelStorie> stories) {
        this.stories = stories;
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
