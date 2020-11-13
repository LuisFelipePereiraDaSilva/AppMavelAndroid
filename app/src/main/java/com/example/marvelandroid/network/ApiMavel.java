package com.example.marvelandroid.network;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ApiMavel extends AsyncTask<Void, Void, JSONArray> {

    private final String url;

    public ApiMavel(String url) {
        this.url = url;
    }

    @Override
    protected JSONArray doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        try {
            String timeStamp = "1605223795";
            String keyPublic = "ac0e6ef268281d3500da1765e90e680b";
            String hast = "f4f2645b556cab829e6c309242778c71";
            URL url = new URL(this.url + "?ts=" + timeStamp + "&apikey=" + keyPublic + "&hash=" + hast);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
            resposta.append(scanner.next());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject json = new JSONObject(resposta.toString());
            JSONObject data = new JSONObject(json.getString("data"));
            JSONArray results = data.getJSONArray("results");
            return results;
        }catch (Exception e) {}

        return null;
    }

}
