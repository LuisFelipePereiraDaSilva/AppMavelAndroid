package com.example.marvelandroid.network.response;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import java.io.InputStream;
import java.net.URL;

public class ResponseImage extends AsyncTask<Void, Void, Drawable> {

    private final String urlImage;

    public ResponseImage(String url) {
        this.urlImage = url;
    }

    @Override
    protected Drawable doInBackground(Void... voids) {
        try {
            InputStream is = (InputStream) new URL(urlImage).getContent();
            Drawable d = Drawable.createFromStream(is, "name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
