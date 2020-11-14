package com.example.marvelandroid;

import com.example.marvelandroid.model.ModelCharacter;
import com.example.marvelandroid.model.ModelComic;
import com.example.marvelandroid.model.ModelCreator;
import com.example.marvelandroid.model.ModelEvent;
import com.example.marvelandroid.model.ModelSerie;
import com.example.marvelandroid.model.ModelStorie;

import org.junit.Test;

import java.util.ArrayList;

public class SerieTest {
    @Test
    public void createSerieWithNameAndResourceURI () {
        try {
            ModelSerie serie = new ModelSerie("Name Serie", "ResourceURI Serie");
            assert (serie != null ? true : false);
        }catch (Exception e) {
            assert (false);
        }
    }

    @Test
    public void createSerieComplete () {
        try {
            ModelSerie serie = new ModelSerie("1", "Name Serie", "Description Serie", "Start year",
                    "End year", "image", new ArrayList<ModelCreator>(), new ArrayList<ModelCharacter>(), new ArrayList<ModelStorie>(),
                    new ArrayList<ModelComic>(), new ArrayList<ModelEvent>(), "ResourceURI Serie");
            assert (serie != null ? true : false);
        }catch (Exception e) {
            assert (false);
        }
    }
}
