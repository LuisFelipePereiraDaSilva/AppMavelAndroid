package com.example.marvelandroid.model;

import java.util.ArrayList;

public class ModelEvent {
    private String id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private String image;
    private ArrayList<ModelCreator> creators;
    private ArrayList<ModelCharacter> characters;
    private ArrayList<ModelStorie> stories;
    private ArrayList<ModelComic> comics;
    private ArrayList<ModelSerie> series;
    private String resourceURI;

    public ModelEvent(String name, String resourceURI) {
        this.name = name;
        this.resourceURI = resourceURI;
    }

    public ModelEvent(String id, String name, String description, String startDate, String endDate, String image, ArrayList<ModelCreator> creators, ArrayList<ModelCharacter> characters, ArrayList<ModelStorie> stories, ArrayList<ModelComic> comics, ArrayList<ModelSerie> series, String resourceURI) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
        this.creators = creators;
        this.characters = characters;
        this.stories = stories;
        this.comics = comics;
        this.series = series;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public ArrayList<ModelSerie> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<ModelSerie> series) {
        this.series = series;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }
}
