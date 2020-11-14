package com.example.marvelandroid;

import com.example.marvelandroid.model.ModelCharacter;
import com.example.marvelandroid.model.ModelComic;
import com.example.marvelandroid.model.ModelCreator;
import com.example.marvelandroid.model.ModelEvent;
import com.example.marvelandroid.model.ModelSerie;
import com.example.marvelandroid.model.ModelStorie;

import org.junit.Test;

import java.util.ArrayList;

public class ComicTest {
    @Test
    public void createComicWithNameAndResourceURI () {
        try {
            ModelComic comic = new ModelComic("Name Comic", "ResourceURI Comic");
            assert (comic != null ? true : false);
        }catch (Exception e) {
            assert (false);
        }
    }

    @Test
    public void createComicComplete () {
        try {
            ModelComic comic = new ModelComic("1", "Name Comic", "Decription Comic", "image",
                    new ArrayList<ModelSerie>(), new ArrayList<ModelCreator>(), new ArrayList<ModelCharacter>(),
                    new ArrayList<ModelStorie>(), new ArrayList<ModelEvent>(),
                    "ResourceURI Comic");
            assert (comic != null ? true : false);
        }catch (Exception e) {
            assert (false);
        }
    }
}
