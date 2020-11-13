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
import com.example.marvelandroid.model.ModelCharacter;
import com.example.marvelandroid.model.ModelComic;
import com.example.marvelandroid.network.response.ResponseCharacter;
import com.example.marvelandroid.network.response.ResponseComic;
import com.example.marvelandroid.network.response.ResponseImage;

import androidx.appcompat.app.AppCompatActivity;

public class ViewDetailsComic extends AppCompatActivity {

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

        getSupportActionBar().setTitle("Quandrinho");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_details_comic);
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
                    final ModelComic comic = new ResponseComic().getComic(urlId);

                    if (comic != null) {
                        final LinearLayout.LayoutParams marginImage = new LinearLayout.LayoutParams
                                ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
                        marginImage.width = LinearLayout.LayoutParams.MATCH_PARENT;
                        marginImage.height = 1000;
                        marginImage.gravity = Gravity.CENTER;

                        handler.post(new Runnable(){
                            public void run(){
                                try {
                                    Drawable d = new ResponseImage(comic.getImage()).execute().get();
                                    imageView.setBackgroundDrawable(d);
                                    imageView.setLayoutParams(marginImage);
                                } catch (Exception e) {
                                    imageView.setBackgroundResource(R.drawable.image_null);
                                    imageView.setLayoutParams(marginImage);
                                }

                                textViewId.setText(comic.getId());

                                textViewName.setText(comic.getName());

                                textViewDescription.setText(comic.getDescription());

                                linearLayoutDetails.addView(ListAux.mountListAuxSerie(context, comic.getSeries()));

                                linearLayoutDetails.addView(ListAux.mountListAuxCreator(context, comic.getCreators()));

                                linearLayoutDetails.addView(ListAux.mountListAuxCharacter(context, comic.getCharacters()));

                                linearLayoutDetails.addView(ListAux.mountListAuxStorie(context, comic.getStories()));

                                linearLayoutDetails.addView(ListAux.mountListAuxEvent(context, comic.getEvents()));

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
