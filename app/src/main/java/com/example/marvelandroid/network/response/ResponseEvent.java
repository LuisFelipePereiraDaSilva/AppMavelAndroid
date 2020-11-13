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

public class ResponseEvent {
    public ArrayList<ModelEvent> getListEvents () throws Exception {
        return getEvents(null);
    }

    public ModelEvent getEvent (String idUrl) throws Exception {
        ArrayList<ModelEvent> list = getEvents(idUrl);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    private ArrayList<ModelEvent> getEvents (String idUrl) throws Exception {
        ArrayList<ModelEvent> events = new ArrayList<>();
        ApiMavel api;
        if (idUrl == null)
            api = new ApiMavel("https://gateway.marvel.com/v1/public/events");
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

            JSONObject storiesAux = object.getJSONObject("stories");
            JSONArray storiesArray = storiesAux.getJSONArray("items");
            ArrayList<ModelStorie> stories = new ArrayList<>();
            for (int j = 0; j < storiesArray.length(); j++) {
                JSONObject storie = storiesArray.getJSONObject(j);
                stories.add(new ModelStorie(storie.getString("name"), storie.getString("resourceURI")));
            }

            JSONObject comicsAux = object.getJSONObject("comics");
            JSONArray comicsArray = comicsAux.getJSONArray("items");
            ArrayList<ModelComic> comics = new ArrayList<>();
            for (int j = 0; j < comicsArray.length(); j++) {
                JSONObject comic = comicsArray.getJSONObject(j);
                comics.add(new ModelComic(comic.getString("name"), comic.getString("resourceURI")));
            }

            JSONObject seriesAux = object.getJSONObject("series");
            JSONArray seriesArray = seriesAux.getJSONArray("items");
            ArrayList<ModelSerie> series = new ArrayList<>();
            for (int j = 0; j < seriesArray.length(); j++) {
                JSONObject serie = seriesArray.getJSONObject(j);
                series.add(new ModelSerie(serie.getString("name"), serie.getString("resourceURI")));
            }

            JSONObject thumbnail = object.getJSONObject("thumbnail");
            String image = thumbnail.getString("path") + "/portrait_xlarge." + thumbnail.getString("extension");

            events.add(new ModelEvent(object.getString("id"), object.getString("title"), object.getString("description"),
                    object.getString("start"), object.getString("end"), image, creators, characters, stories, comics, series,
                    object.getString("resourceURI")));
        }

        return events;
    }
}
