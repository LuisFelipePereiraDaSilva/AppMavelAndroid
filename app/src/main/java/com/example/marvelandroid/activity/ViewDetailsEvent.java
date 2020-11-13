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
import com.example.marvelandroid.model.ModelComic;
import com.example.marvelandroid.model.ModelEvent;
import com.example.marvelandroid.network.response.ResponseComic;
import com.example.marvelandroid.network.response.ResponseEvent;
import com.example.marvelandroid.network.response.ResponseImage;

import androidx.appcompat.app.AppCompatActivity;

public class ViewDetailsEvent extends AppCompatActivity {

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
    private  TextView textViewStartDate;
    private  TextView textViewEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Evento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_details_event);
        textViewLoading = (TextView) findViewById(R.id.textViewLoading);
        linearLayoutDetails = (LinearLayout) findViewById(R.id.linearLayoutDetails);
        imageView = (ImageView) findViewById(R.id.imageView);
        textViewId = (TextView) findViewById(R.id.textViewId);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);
        textViewStartDate = (TextView) findViewById(R.id.textViewStartDate);
        textViewEndDate = (TextView) findViewById(R.id.textViewEndData);

        mountLayout();
    }

    private void mountLayout () {
        final Context context = this;

        new Thread() {
            public void run() {
                try {
                    final ModelEvent event = new ResponseEvent().getEvent(urlId);

                    if (event != null) {
                        final LinearLayout.LayoutParams marginImage = new LinearLayout.LayoutParams
                                ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
                        marginImage.width = LinearLayout.LayoutParams.MATCH_PARENT;
                        marginImage.height = 1000;
                        marginImage.gravity = Gravity.CENTER;

                        handler.post(new Runnable(){
                            public void run(){
                                try {
                                    Drawable d = new ResponseImage(event.getImage()).execute().get();
                                    imageView.setBackgroundDrawable(d);
                                    imageView.setLayoutParams(marginImage);
                                } catch (Exception e) {
                                    imageView.setBackgroundResource(R.drawable.image_null);
                                    imageView.setLayoutParams(marginImage);
                                }

                                textViewId.setText(event.getId());

                                textViewName.setText(event.getName());

                                textViewDescription.setText(event.getDescription());

                                textViewStartDate.setText(event.getStartDate());

                                textViewEndDate.setText(event.getEndDate());

                                linearLayoutDetails.addView(ListAux.mountListAuxCreator(context, event.getCreators()));

                                linearLayoutDetails.addView(ListAux.mountListAuxCharacter(context, event.getCharacters()));

                                linearLayoutDetails.addView(ListAux.mountListAuxStorie(context, event.getStories()));

                                linearLayoutDetails.addView(ListAux.mountListAuxComic(context, event.getComics()));

                                linearLayoutDetails.addView(ListAux.mountListAuxSerie(context, event.getSeries()));


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
                            Toast.makeText(context, "Esse evento não foi encontrado.", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }.start();
    }
}
