package com.example.marvelandroid;

import com.example.marvelandroid.model.ModelCharacter;
import com.example.marvelandroid.model.ModelComic;
import com.example.marvelandroid.model.ModelEvent;
import com.example.marvelandroid.model.ModelSerie;
import com.example.marvelandroid.model.ModelStorie;

import org.junit.Test;

import java.util.ArrayList;

public class CharacterTest {

    @Test
    public void createCharacterWithNameAndResourceURI () {
        try {
            ModelCharacter character = new ModelCharacter("Name Character", "ResourceURI Character");
            assert (character != null ? true : false);
        }catch (Exception e) {
            assert (false);
        }
    }

    @Test
    public void createCharacterComplete () {
        try {
            ModelCharacter character = new ModelCharacter("1", "Name Character", "Decription Character", "image",
                    new ArrayList<ModelComic>(), new ArrayList<ModelSerie>(), new ArrayList<ModelStorie>(), new ArrayList<ModelEvent>(),
                    "ResourceURI Character");
            assert (character != null ? true : false);
        }catch (Exception e) {
            assert (false);
        }
    }
}
