package com.example.marvelandroid.model;

import java.util.ArrayList;

public class ModelComic {
    private String id;
    private String name;
    private String description;
    private String price;
    private String digitalPurchasePrice;
    private String image;
    private ArrayList<ModelSerie> series;
    private ArrayList<ModelCreator> creators;
    private ArrayList<ModelCharacter> characters;
    private ArrayList<ModelStorie> stories;
    private ArrayList<ModelEvent> events;
    private String resourceURI;

    public ModelComic(String name, String resourceURI) {
        this.name = name;
        this.resourceURI = resourceURI;
    }

    public ModelComic(String id, String name, String description, String price, String digitalPurchasePrice, String image, ArrayList<ModelSerie> series, ArrayList<ModelCreator> creators, ArrayList<ModelCharacter> characters, ArrayList<ModelStorie> stories, ArrayList<ModelEvent> events, String resourceURI) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.digitalPurchasePrice = digitalPurchasePrice;
        this.image = image;
        this.series = series;
        this.creators = creators;
        this.characters = characters;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDigitalPurchasePrice() {
        return digitalPurchasePrice;
    }

    public void setDigitalPurchasePrice(String digitalPurchasePrice) {
        this.digitalPurchasePrice = digitalPurchasePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<ModelSerie> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<ModelSerie> series) {
        this.series = series;
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
