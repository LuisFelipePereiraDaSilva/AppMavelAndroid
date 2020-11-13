package com.example.marvelandroid.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marvelandroid.R;
import com.example.marvelandroid.activity.shared.ListAux;
import com.example.marvelandroid.model.ModelCharacter;
import com.example.marvelandroid.network.response.ResponseCharacter;
import com.example.marvelandroid.network.response.ResponseImage;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ViewDetailsCharacter extends AppCompatActivity {

    private static String urlId = "";
    public static void setUrlId(String url) {
        urlId = url;
    }

    private LinearLayout linearLayoutDetails;
    private TextView textViewLoading;
    private Handler handler = new Handler();
    private ImageView imageView;
    private TextView textViewId;
    private TextView textViewName;
    private  TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_character);

        getSupportActionBar().setTitle("Personagem");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewLoading = (TextView) findViewById(R.id.textViewLoading);
        linearLayoutDetails = (LinearLayout) findViewById(R.id.linearLayoutDetails);
        imageView = (ImageView) findViewById(R.id.imageView);
        textViewId = (TextView) findViewById(R.id.textViewId);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);

        mountLayout();
    }

    private void mountLayout () {
        final Context context = this;

        new Thread() {
            public void run() {
                try {
                    final ModelCharacter character = new ResponseCharacter().getCharacter(urlId);

                    if (character != null) {
                        final LinearLayout.LayoutParams marginImage = new LinearLayout.LayoutParams
                                ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
                        marginImage.width = LinearLayout.LayoutParams.MATCH_PARENT;
                        marginImage.height = 1000;
                        marginImage.gravity = Gravity.CENTER;

                        handler.post(new Runnable(){
                            public void run(){
                                try {
                                    Drawable d = new ResponseImage(character.getImage()).execute().get();
                                    imageView.setBackgroundDrawable(d);
                                    imageView.setLayoutParams(marginImage);
                                } catch (Exception e) {
                                    imageView.setBackgroundResource(R.drawable.image_null);
                                    imageView.setLayoutParams(marginImage);
                                }

                                textViewId.setText(character.getId());

                                textViewName.setText(character.getName());

                                textViewDescription.setText(character.getDescription());

                                linearLayoutDetails.addView(ListAux.mountListAuxComic(context, character.getComics()));

                                linearLayoutDetails.addView(ListAux.mountListAuxSerie(context, character.getSeries()));

                                linearLayoutDetails.addView(ListAux.mountListAuxStorie(context, character.getStories()));

                                linearLayoutDetails.addView(ListAux.mountListAuxEvent(context, character.getEvents()));

                                textViewLoading.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                    else
                        handler.post(new Runnable(){
                            public void run(){
                                textViewLoading.setText("Esse personagem não foi encontrado");
                            }
                        });
                } catch (Exception e) {
                    handler.post(new Runnable(){
                        public void run() {
                            Toast.makeText(context, "Esse personagem não foi encontrado.", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }.start();
    }
}
