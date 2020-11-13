package com.example.marvelandroid.network.response;

import com.example.marvelandroid.model.ModelCharacter;
import com.example.marvelandroid.model.ModelComic;
import com.example.marvelandroid.model.ModelCreator;
import com.example.marvelandroid.model.ModelEvent;
import com.example.marvelandroid.model.ModelSerie;
import com.example.marvelandroid.model.ModelStorie;
import com.example.marvelandroid.network.ApiMavel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResponseComic {
    public ArrayList<ModelComic> getListComics () throws Exception {
        return getComics(null);
    }

    public ModelComic getComic (String idUrl) throws Exception {
        ArrayList<ModelComic> list = getComics(idUrl);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    private ArrayList<ModelComic> getComics (String idUrl) throws Exception {
        ArrayList<ModelComic> comics = new ArrayList<>();
        ApiMavel api;
        if (idUrl == null)
            api = new ApiMavel("https://gateway.marvel.com/v1/public/comics");
        else
            api = new ApiMavel(idUrl);

        JSONArray result = api.execute().get();

        for (int i = 0; i < result.length(); i++) {
            JSONObject object = result.getJSONObject(i);

            JSONObject seriesAux = object.getJSONObject("series");
            ArrayList<ModelSerie> series = new ArrayList<>();
            series.add(new ModelSerie(seriesAux.getString("name"), seriesAux.getString("resourceURI")));

            JSONObject creatorsAux = object.getJSONObject("creators");
            JSONArray creatorsArray = creatorsAux.getJSONArray("items");
            ArrayList<ModelCreator> creators = new ArrayList<>();
            for (int j = 0; j < creatorsArray.length(); j++) {
                JSONObject creator = creatorsArray.getJSONObject(j);
                creators.add(new ModelCreator(creator.getString("name"), creator.getString("resourceURI")));
            }

            JSONObject charactersAux = object.getJSONObject("characters");
            JSONArray charactersArray = charactersAux.getJSONArray("items");
            ArrayList<ModelCharacter> characters = new ArrayList<>();
            for (int j = 0; j < charactersArray.length(); j++) {
                JSONObject character = charactersArray.getJSONObject(j);
                characters.add(new ModelCharacter(character.getString("name"), character.getString("resourceURI")));
            }

            JSONObject storiesAux = object.getJSONObject("stories");
            JSONArray storiesArray = storiesAux.getJSONArray("items");
            ArrayList<ModelStorie> stories = new ArrayList<>();
            for (int j = 0; j < storiesArray.length(); j++) {
                JSONObject storie = storiesArray.getJSONObject(j);
                stories.add(new ModelStorie(storie.getString("name"), storie.getString("resourceURI")));
            }

            JSONObject eventsAux = object.getJSONObject("events");
            JSONArray eventsArray = eventsAux.getJSONArray("items");
            ArrayList<ModelEvent> events = new ArrayList<>();
            for (int j = 0; j < eventsArray.length(); j++) {
                JSONObject event = eventsArray.getJSONObject(j);
                events.add(new ModelEvent(event.getString("name"), event.getString("resourceURI")));
            }

            JSONObject thumbnail = object.getJSONObject("thumbnail");
            String image = thumbnail.getString("path") + "/portrait_xlarge." + thumbnail.getString("extension");

            comics.add(new ModelComic(object.getString("id"), object.getString("title"), object.getString("description"),
                    image, series, creators, characters, stories, events, object.getString("resourceURI")));
        }

        return comics;
    }
}
