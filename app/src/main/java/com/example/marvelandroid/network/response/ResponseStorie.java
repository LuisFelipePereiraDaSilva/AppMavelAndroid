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

public class ResponseStorie {
    public ArrayList<ModelStorie> getListStories () throws Exception {
        return getStories(null);
    }

    public ModelStorie getStorie (String idUrl) throws Exception {
        ArrayList<ModelStorie> list = getStories(idUrl);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    private ArrayList<ModelStorie> getStories (String idUrl) throws Exception {
        ArrayList<ModelStorie> stories = new ArrayList<>();
        ApiMavel api;
        if (idUrl == null)
            api = new ApiMavel("https://gateway.marvel.com/v1/public/stories");
        else
            api = new ApiMavel(idUrl);

        JSONArray result = api.execute().get();

        for (int i = 0; i < result.length(); i++) {
            JSONObject object = result.getJSONObject(i);

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

            JSONObject seriesAux = object.getJSONObject("series");
            JSONArray seriesArray = seriesAux.getJSONArray("items");
            ArrayList<ModelSerie> series = new ArrayList<>();
            for (int j = 0; j < seriesArray.length(); j++) {
                JSONObject serie = seriesArray.getJSONObject(j);
                series.add(new ModelSerie(serie.getString("name"), serie.getString("resourceURI")));
            }

            JSONObject comicsAux = object.getJSONObject("comics");
            JSONArray comicsArray = comicsAux.getJSONArray("items");
            ArrayList<ModelComic> comics = new ArrayList<>();
            for (int j = 0; j < comicsArray.length(); j++) {
                JSONObject comic = comicsArray.getJSONObject(j);
                comics.add(new ModelComic(comic.getString("name"), comic.getString("resourceURI")));
            }

            JSONObject eventsAux = object.getJSONObject("events");
            JSONArray eventsArray = eventsAux.getJSONArray("items");
            ArrayList<ModelEvent> events = new ArrayList<>();
            for (int j = 0; j < eventsArray.length(); j++) {
                JSONObject event = eventsArray.getJSONObject(j);
                events.add(new ModelEvent(event.getString("name"), event.getString("resourceURI")));
            }

            stories.add(new ModelStorie(object.getString("id"), object.getString("title"), object.getString("description"),
                    creators, characters, series, comics, events, object.getString("resourceURI")));
        }

        return stories;
    }
}
