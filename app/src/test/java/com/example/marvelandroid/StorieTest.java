package com.example.marvelandroid;

import com.example.marvelandroid.model.ModelCharacter;
import com.example.marvelandroid.model.ModelComic;
import com.example.marvelandroid.model.ModelCreator;
import com.example.marvelandroid.model.ModelEvent;
import com.example.marvelandroid.model.ModelSerie;
import com.example.marvelandroid.model.ModelStorie;

import org.junit.Test;

import java.util.ArrayList;

public class StorieTest {
    @Test
    public void createStorieWithNameAndResourceURI () {
        try {
            ModelStorie storie = new ModelStorie("Name Storie", "ResourceURI Storie");
            assert (storie != null ? true : false);
        }catch (Exception e) {
            assert (false);
        }
    }

    @Test
    public void createStorieComplete () {
        try {
            ModelStorie storie = new ModelStorie("1", "Name Serie", "Description Serie",
                    new ArrayList<ModelCreator>(), new ArrayList<ModelCharacter>(), new ArrayList<ModelSerie>(),
                    new ArrayList<ModelComic>(), new ArrayList<ModelEvent>(), "ResourceURI Serie");
            assert (storie != null ? true : false);
        }catch (Exception e) {
            assert (false);
        }
    }
}
