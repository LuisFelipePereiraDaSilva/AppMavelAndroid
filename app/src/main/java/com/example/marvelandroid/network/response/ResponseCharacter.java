package com.example.marvelandroid.network.response;

import com.example.marvelandroid.model.ModelCharacter;
import com.example.marvelandroid.model.ModelComic;
import com.example.marvelandroid.model.ModelEvent;
import com.example.marvelandroid.model.ModelSerie;
import com.example.marvelandroid.model.ModelStorie;
import com.example.marvelandroid.network.ApiMavel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResponseCharacter {

    public ArrayList<ModelCharacter> getListCharacters () throws Exception {
        return getCharacters(null);
    }

    public ModelCharacter getCharacter (String idUrl) throws Exception {
        ArrayList<ModelCharacter> list = getCharacters(idUrl);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    private ArrayList<ModelCharacter> getCharacters (String idUrl) throws Exception {
        ArrayList<ModelCharacter> characters = new ArrayList<>();
        ApiMavel api;
        if (idUrl == null)
            api = new ApiMavel("https://gateway.marvel.com/v1/public/characters");
        else
            api = new ApiMavel(idUrl);

        JSONArray result = api.execute().get();

        for (int i = 0; i < result.length(); i++) {
            JSONObject object = result.getJSONObject(i);

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

            characters.add(new ModelCharacter(object.getString("id"), object.getString("name"), object.getString("description"),
                    image, comics, series, stories, events, object.getString("resourceURI")));
        }

        return characters;
    }
}
