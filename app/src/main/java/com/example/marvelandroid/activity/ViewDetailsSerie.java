package com.example.marvelandroid.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marvelandroid.R;
import com.example.marvelandroid.activity.shared.ListAux;
import com.example.marvelandroid.model.ModelEvent;
import com.example.marvelandroid.model.ModelSerie;
import com.example.marvelandroid.network.response.ResponseEvent;
import com.example.marvelandroid.network.response.ResponseImage;
import com.example.marvelandroid.network.response.ResponseSerie;

import androidx.appcompat.app.AppCompatActivity;

public class ViewDetailsSerie extends AppCompatActivity {

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
    private  TextView textViewStartYear;
    private  TextView textViewEndYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Série");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_details_serie);
        textViewLoading = (TextView) findViewById(R.id.textViewLoading);
        linearLayoutDetails = (LinearLayout) findViewById(R.id.linearLayoutDetails);
        imageView = (ImageView) findViewById(R.id.imageView);
        textViewId = (TextView) findViewById(R.id.textViewId);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);
        textViewStartYear = (TextView) findViewById(R.id.textViewStartYear);
        textViewEndYear = (TextView) findViewById(R.id.textViewEndYear);

        mountLayout();
    }

    private void mountLayout () {
        final Context context = this;

        new Thread() {
            public void run() {
                try {
                    final ModelSerie serie = new ResponseSerie().getSerie(urlId);

                    if (serie != null) {
                        final LinearLayout.LayoutParams marginImage = new LinearLayout.LayoutParams
                                ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
                        marginImage.width = LinearLayout.LayoutParams.MATCH_PARENT;
                        marginImage.height = 1000;
                        marginImage.gravity = Gravity.CENTER;

                        handler.post(new Runnable(){
                            public void run(){
                                try {
                                    Drawable d = new ResponseImage(serie.getImage()).execute().get();
                                    imageView.setBackgroundDrawable(d);
                                    imageView.setLayoutParams(marginImage);
                                } catch (Exception e) {
                                    imageView.setBackgroundResource(R.drawable.image_null);
                                    imageView.setLayoutParams(marginImage);
                                }

                                textViewId.setText(serie.getId());

                                textViewName.setText(serie.getName());

                                textViewDescription.setText(serie.getDescription());

                                textViewStartYear.setText(serie.getStartYear());

                                textViewEndYear.setText(serie.getEndYear());

                                linearLayoutDetails.addView(ListAux.mountListAuxCreator(context, serie.getCreators()));

                                linearLayoutDetails.addView(ListAux.mountListAuxCharacter(context, serie.getCharacters()));

                                linearLayoutDetails.addView(ListAux.mountListAuxStorie(context, serie.getStories()));

                                linearLayoutDetails.addView(ListAux.mountListAuxComic(context, serie.getComics()));

                                linearLayoutDetails.addView(ListAux.mountListAuxEvent(context, serie.getEvents()));


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
                    Toast.makeText(context, "Ocorreu um erro ao carregar os personagens.", Toast.LENGTH_LONG).show();
                }
            }
        }.start();
    }
}
