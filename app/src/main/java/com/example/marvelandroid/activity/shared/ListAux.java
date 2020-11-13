package com.example.marvelandroid.activity.shared;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.marvelandroid.activity.ViewDetailsCharacter;
import com.example.marvelandroid.activity.ViewDetailsComic;
import com.example.marvelandroid.activity.ViewDetailsCreator;
import com.example.marvelandroid.activity.ViewDetailsEvent;
import com.example.marvelandroid.activity.ViewDetailsSerie;
import com.example.marvelandroid.activity.ViewDetailsStorie;
import com.example.marvelandroid.activity.ViewListCharacters;
import com.example.marvelandroid.model.ModelCharacter;
import com.example.marvelandroid.model.ModelComic;
import com.example.marvelandroid.model.ModelCreator;
import com.example.marvelandroid.model.ModelEvent;
import com.example.marvelandroid.model.ModelSerie;
import com.example.marvelandroid.model.ModelStorie;

import java.util.ArrayList;

public class ListAux {

    private String name;
    private String resourceURI;

    public ListAux (String name, String resourceURI) {
        this.name = name;
        this.resourceURI = resourceURI;
    }

    public static LinearLayout mountListAuxCharacter(Context context, ArrayList<ModelCharacter> list) {
        ArrayList<ListAux> listAux = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listAux.add(new ListAux(list.get(i).getName(), list.get(i).getResourceURI()));
        }
        return mountListAux(context, "Personagens", listAux);
    }

    public static LinearLayout mountListAuxComic(Context context, ArrayList<ModelComic> list) {
        ArrayList<ListAux> listAux = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listAux.add(new ListAux(list.get(i).getName(), list.get(i).getResourceURI()));
        }
        return mountListAux(context, "Quandrinhos", listAux);
    }

    public static LinearLayout mountListAuxCreator(Context context, ArrayList<ModelCreator> list) {
        ArrayList<ListAux> listAux = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listAux.add(new ListAux(list.get(i).getName(), list.get(i).getResourceURI()));
        }
        return mountListAux(context, "Criadores", listAux);
    }

    public static LinearLayout mountListAuxEvent(Context context, ArrayList<ModelEvent> list) {
        ArrayList<ListAux> listAux = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listAux.add(new ListAux(list.get(i).getName(), list.get(i).getResourceURI()));
        }
        return mountListAux(context, "Eventos", listAux);
    }

    public static LinearLayout mountListAuxSerie(Context context, ArrayList<ModelSerie> list) {
        ArrayList<ListAux> listAux = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listAux.add(new ListAux(list.get(i).getName(), list.get(i).getResourceURI()));
        }
        return mountListAux(context, "Séries", listAux);
    }

    public static LinearLayout mountListAuxStorie(Context context, ArrayList<ModelStorie> list) {
        ArrayList<ListAux> listAux = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listAux.add(new ListAux(list.get(i).getName(), list.get(i).getResourceURI()));
        }
        return mountListAux(context, "Histórias", listAux);
    }

    private static LinearLayout mountListAux(final Context context, final String titleList, final ArrayList<ListAux> list) {

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams marginTitle = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
        marginTitle.gravity = Gravity.LEFT;
        marginTitle.topMargin = 30;

        TextView titleTextView = new TextView(context);
        titleTextView.setText(titleList + ":");
        titleTextView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        titleTextView.setTextColor(Color.rgb(0, 0, 0));
        titleTextView.setPadding(0,10,10,10);
        titleTextView.setTypeface(null, Typeface.BOLD);
        titleTextView.setTextSize(16);
        titleTextView.setLayoutParams(marginTitle);
        layout.addView(titleTextView);

        for (int i = 0; i < list.size(); i++) {
            LinearLayout.LayoutParams marginLayout = new LinearLayout.LayoutParams
                    ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
            marginLayout.width = LinearLayout.LayoutParams.MATCH_PARENT;
            marginLayout.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            marginLayout.topMargin = 20;

            LinearLayout layoutAux = new LinearLayout(context);
            layoutAux.setOrientation(LinearLayout.VERTICAL);
            layoutAux.setLayoutParams(marginLayout);

            TextView nameTextView = new TextView(context);
            nameTextView.setText(list.get(i).name);
            nameTextView.setTextColor(Color.rgb(0, 0, 0));
            nameTextView.setPadding(10,10,10,0);
            nameTextView.setTextSize(14);
            layoutAux.addView(nameTextView);

            TextView moreDetailsButton = new TextView(context);
            moreDetailsButton.setText("Mais detalhes");
            moreDetailsButton.setTextColor(Color.rgb(0, 0, 255));
            moreDetailsButton.setPadding(10,00,10,0);
            moreDetailsButton.setTextSize(14);
            final int j = i;
            moreDetailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (titleList.equals("Personagens")) {
                        ViewDetailsCharacter.setUrlId(list.get(j).resourceURI);
                        context.startActivity(new Intent(context, ViewDetailsCharacter.class));
                    } else if (titleList.equals("Quandrinhos")) {
                        ViewDetailsComic.setUrlId(list.get(j).resourceURI);
                        context.startActivity(new Intent(context, ViewDetailsComic.class));
                    } else if (titleList.equals("Criadores")) {
                        ViewDetailsCreator.setUrlId(list.get(j).resourceURI);
                        context.startActivity(new Intent(context, ViewDetailsCreator.class));
                    } else if (titleList.equals("Eventos")) {
                        ViewDetailsEvent.setUrlId(list.get(j).resourceURI);
                        context.startActivity(new Intent(context, ViewDetailsEvent.class));
                    } else if (titleList.equals("Séries")) {
                        ViewDetailsSerie.setUrlId(list.get(j).resourceURI);
                        context.startActivity(new Intent(context, ViewDetailsSerie.class));
                    } else if (titleList.equals("Histórias")) {
                        ViewDetailsStorie.setUrlId(list.get(j).resourceURI);
                        context.startActivity(new Intent(context, ViewDetailsStorie.class));
                    }
                }
            });
            layoutAux.addView(moreDetailsButton);

            layout.addView(layoutAux);
        }

        return layout;
    }
}
