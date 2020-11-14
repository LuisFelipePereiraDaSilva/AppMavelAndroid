package com.example.marvelandroid;

import com.example.marvelandroid.model.ModelComic;
import com.example.marvelandroid.model.ModelCreator;
import com.example.marvelandroid.model.ModelEvent;
import com.example.marvelandroid.model.ModelSerie;
import com.example.marvelandroid.model.ModelStorie;

import org.junit.Test;

import java.util.ArrayList;

public class CreatorTest {
    @Test
    public void createCreatorWithNameAndResourceURI () {
        try {
            ModelCreator creator = new ModelCreator("Name Creator", "ResourceURI Creator");
            assert (creator != null ? true : false);
        }catch (Exception e) {
            assert (false);
        }
    }

    @Test
    public void createEventComplete () {
        try {
            ModelCreator creator = new ModelCreator("1", "Name Creator", "image", new ArrayList<ModelComic>(),
                    new ArrayList<ModelSerie>(), new ArrayList<ModelStorie>(), new ArrayList<ModelEvent>(), "ResourceURI Creator");
            assert (creator != null ? true : false);
        }catch (Exception e) {
            assert (false);
        }
    }
}
