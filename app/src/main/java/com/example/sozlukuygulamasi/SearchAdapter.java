package com.example.sozlukuygulamasi;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SearchAdapter extends ArrayAdapter<String> {


    private final ArrayList<String> turkce;
    private final ArrayList<String> ingilizce;
    private final Activity context;


    public SearchAdapter(Activity context,  ArrayList<String> turkce, ArrayList<String> ingilizce) {
        super(context, R.layout.sablon, turkce);

        this.turkce = turkce;
        this.ingilizce = ingilizce;
        this.context = context;


    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater lyt = context.getLayoutInflater();
        View listeleman = lyt.inflate(R.layout.sablonsearch, null, true);


        TextView ust = (TextView) listeleman.findViewById(R.id.ust);
        TextView alt = (TextView) listeleman.findViewById(R.id.alt);


        ust.setText(turkce.get(position));
        alt.setText(ingilizce.get(position));
        return listeleman;
    }
}
