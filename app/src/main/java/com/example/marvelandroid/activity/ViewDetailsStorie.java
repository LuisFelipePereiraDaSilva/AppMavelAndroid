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
import com.example.marvelandroid.model.ModelSerie;
import com.example.marvelandroid.model.ModelStorie;
import com.example.marvelandroid.network.response.ResponseImage;
import com.example.marvelandroid.network.response.ResponseSerie;
import com.example.marvelandroid.network.response.ResponseStorie;

import androidx.appcompat.app.AppCompatActivity;

public class ViewDetailsStorie extends AppCompatActivity {

    private static String urlId = "";
    public static void setUrlId(String url) {
        urlId = url;
    }

    private LinearLayout linearLayoutDetails;
    private TextView textViewLoading;
    private Handler handler = new Handler();
    private TextView textViewId;
    private TextView textViewName;
    private  TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("História");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_details_storie);
        textViewLoading = (TextView) findViewById(R.id.textViewLoading);
        linearLayoutDetails = (LinearLayout) findViewById(R.id.linearLayoutDetails);
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
                    final ModelStorie storie = new ResponseStorie().getStorie(urlId);

                    if (storie != null) {
                        final LinearLayout.LayoutParams marginImage = new LinearLayout.LayoutParams
                                ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
                        marginImage.width = LinearLayout.LayoutParams.MATCH_PARENT;
                        marginImage.height = 1000;
                        marginImage.gravity = Gravity.CENTER;

                        handler.post(new Runnable(){
                            public void run(){
                                textViewId.setText(storie.getId());

                                textViewName.setText(storie.getName());

                                textViewDescription.setText(storie.getDescription());

                                linearLayoutDetails.addView(ListAux.mountListAuxCreator(context, storie.getCreators()));

                                linearLayoutDetails.addView(ListAux.mountListAuxCharacter(context, storie.getCharacters()));

                                linearLayoutDetails.addView(ListAux.mountListAuxSerie(context, storie.getSeries()));

                                linearLayoutDetails.addView(ListAux.mountListAuxComic(context, storie.getComics()));

                                linearLayoutDetails.addView(ListAux.mountListAuxEvent(context, storie.getEvents()));


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
