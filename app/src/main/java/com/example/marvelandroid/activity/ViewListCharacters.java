package com.example.marvelandroid.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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
import com.example.marvelandroid.model.ModelCharacter;
import com.example.marvelandroid.network.response.ResponseCharacter;

import java.io.InputStream;
import java.net.URL;
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
            InputStream is = (InputStream) new URL(img).getContent();
            Drawable d = Drawable.createFromStream(is, "");
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
                    ArrayList<LinearLayout> layouts = new ArrayList<LinearLayout>();

                    ArrayList<ModelCharacter> list = new ResponseCharacter().getListCharacters();

                    for (int i = 0; i < list.size(); i++) {
                        layouts.add(montarElementoLinearLayoutInicio(list.get(i).getName(), list.get(i).getImage()));
                        layouts.get(layouts.size() - 1).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    }

                    LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams
                            ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
                    margin.gravity = Gravity.LEFT;

                    LinearLayout layout = new LinearLayout(context);
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                    layout.setLayoutParams(margin);

                    LinearLayout.LayoutParams margin2 = new LinearLayout.LayoutParams
                            ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
                    margin2.gravity = Gravity.CENTER;

                    final LinearLayout layoutsAux = new LinearLayout(context);
                    layoutsAux.setOrientation(LinearLayout.VERTICAL);
                    layoutsAux.setLayoutParams(margin2);

                    float width = 0;
                    boolean flag = false;

                    for (int i = 0; i < layouts.size(); i++) {
                        flag = false;
                        if (width + 520 < largura) {
                            layout.addView(layouts.get(i));
                            width += 520;
                        } else {
                            layoutsAux.addView(layout);
                            layout = new LinearLayout(context);
                            layout.setOrientation(LinearLayout.HORIZONTAL);
                            layout.setLayoutParams(margin);
                            width = 0;
                            flag = true;
                            i--;
                        }
                    }

                    if (!flag)
                        layoutsAux.addView(layout);

                    handler.post(new Runnable(){
                        public void run(){
                            if (layoutsAux.getChildCount() > 0)
                                textViewLoading.setText("");
                            else
                                textViewLoading.setText("Nenhum personagem encontrado");
                            linearLayoutListCharacters.addView(layoutsAux);
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(context, "Ocorreu um erro ao carregar os personagens.", Toast.LENGTH_LONG).show();
                }
            }
        }.start();
    }
}
