package com.example.marvelandroid;

import com.example.marvelandroid.model.ModelCharacter;
import com.example.marvelandroid.model.ModelComic;
import com.example.marvelandroid.model.ModelCreator;
import com.example.marvelandroid.model.ModelEvent;
import com.example.marvelandroid.model.ModelSerie;
import com.example.marvelandroid.model.ModelStorie;

import org.junit.Test;

import java.util.ArrayList;

public class EventTest {
    @Test
    public void createEventWithNameAndResourceURI () {
        try {
            ModelEvent event = new ModelEvent("Name Event", "ResourceURI Event");
            assert (event != null ? true : false);
        }catch (Exception e) {
            assert (false);
        }
    }

    @Test
    public void createEventComplete () {
        try {
            ModelEvent event = new ModelEvent("1", "Name Creator", "Description Event", "Start date",
                    "End date", "image", new ArrayList<ModelCreator>(), new ArrayList<ModelCharacter>(), new ArrayList<ModelStorie>(),
                    new ArrayList<ModelComic>(), new ArrayList<ModelSerie>(), "ResourceURI Event");
            assert (event != null ? true : false);
        }catch (Exception e) {
            assert (false);
        }
    }
}
