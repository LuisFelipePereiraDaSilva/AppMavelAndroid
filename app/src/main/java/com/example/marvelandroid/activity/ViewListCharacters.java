package com.example.marvelandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marvelandroid.R;
import com.example.marvelandroid.model.ModelCharacter;
import com.example.marvelandroid.network.response.ResponseCharacter;
import com.example.marvelandroid.network.response.ResponseImage;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ViewListCharacters  extends AppCompatActivity {

    private LinearLayout linearLayoutListCharacters;
    private TextView textViewLoading;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_characters);

        getSupportActionBar().setTitle("Início");

        textViewLoading = (TextView) findViewById(R.id.textViewLoading);
        linearLayoutListCharacters = (LinearLayout) findViewById(R.id.linearLayoutListCharacters);

        linearLayoutListCharacters.getViewTreeObserver().addOnGlobalLayoutListener(new
        ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linearLayoutListCharacters.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                float largura = linearLayoutListCharacters.getWidth();
                montarLinearLayoutCharacters(largura);
            }
        });
    }

    private LinearLayout montarElementoLinearLayoutInicio(String title, String img){

        View imageView = null;
        LinearLayout.LayoutParams marginImage = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
        marginImage.width = 380;
        marginImage.height = 380;
        marginImage.gravity = Gravity.CENTER;

        try {
            Drawable d = new ResponseImage(img).execute().get();
            ImageView image = new ImageView(this);
            image.setImageDrawable(d);
            image.setLayoutParams(marginImage);
            imageView = image;
        } catch (Exception e) {
            ImageView image = new ImageView(this);
            image.setImageResource(R.drawable.image_null);
            image.setLayoutParams(marginImage);
            imageView = image;
        }

        LinearLayout.LayoutParams marginTitle = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
        marginTitle.gravity = Gravity.CENTER;

        TextView titleTextView = new TextView(this);
        titleTextView.setText(title);
        titleTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        titleTextView.setTextColor(Color.rgb(0, 0, 0));
        titleTextView.setPadding(10,10,10,10);
        titleTextView.setTypeface(null, Typeface.BOLD);
        titleTextView.setLayoutParams(marginTitle);

        LinearLayout.LayoutParams marginContainer = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
        marginContainer.width = 500;
        marginContainer.height = 470;
        marginContainer.leftMargin = 10;
        marginContainer.rightMargin = 10;
        marginContainer.topMargin = 10;
        marginContainer.bottomMargin = 10;

        LinearLayout container = new LinearLayout(this);
        container.setBackgroundResource(R.drawable.button_custom_list);
        container.setLayoutParams(marginContainer);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setGravity(Gravity.CENTER);

        container.addView(imageView);
        container.addView(titleTextView);

        return container;
    }
    private void montarLinearLayoutCharacters(final float largura){
        final Context context = this;
        new Thread() {
            public void run() {
                try {
                    final ArrayList<ModelCharacter> list = new ResponseCharacter().getListCharacters();

                    LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams
                            ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
                    margin.gravity = Gravity.LEFT;

                    LinearLayout layout = new LinearLayout(context);
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                    layout.setLayoutParams(margin);

                    LinearLayout.LayoutParams margin2 = new LinearLayout.LayoutParams
                            ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
                    margin2.gravity = Gravity.CENTER;

                    float width = 0;

                    for (int j = 0; j < list.size(); j++) {
                        final int i = j;
                        LinearLayout layoutAux = montarElementoLinearLayoutInicio(list.get(i).getName(), list.get(i).getImage());
                        layoutAux.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ViewDetailsCharacter.setUrlId(list.get(i).getResourceURI());
                                startActivity(new Intent(ViewListCharacters.this, ViewDetailsCharacter.class));
                            }
                        });

                        if (width + 520 < largura) {
                            layout.addView(layoutAux);
                            width += 520;
                        } else {
                            final LinearLayout l = layout;
                            handler.post(new Runnable() {
                                public void run() {
                                    try {
                                        if(l.getParent() != null) {
                                            ((ViewGroup)l.getParent()).removeView(l); // <- fix
                                        }
                                        linearLayoutListCharacters.addView(l);
                                    }catch (Exception e) {
                                        System.out.println();
                                    }
                                }
                            });
                            layout = new LinearLayout(context);
                            layout.setOrientation(LinearLayout.HORIZONTAL);
                            layout.setLayoutParams(margin);
                            layout.addView(layoutAux);
                            width = 520;
                        }
                    }

                    final LinearLayout l = layout;
                    handler.post(new Runnable(){
                        public void run(){
                            if(l.getParent() != null) {
                                ((ViewGroup)l.getParent()).removeView(l);
                            }
                            linearLayoutListCharacters.addView(l);

                            if (list.size() > 0)
                                textViewLoading.setText("");
                            else
                                textViewLoading.setText("Nenhum personagem encontrado");
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(context, "Ocorreu um erro ao carregar os personagens.", Toast.LENGTH_LONG).show();
                }
            }
        }.start();
    }
}
