package com.example.sozlukuygulamasi;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<String> {

    private final ArrayList<Integer> id;
    private final ArrayList<String> turkce;
    private final ArrayList<String> ingilizce;
    private final Activity context;


    public MyAdapter(Activity context, ArrayList<Integer> id, ArrayList<String> turkce, ArrayList<String> ingilizce) {
        super(context, R.layout.sablon, turkce);
        this.id = id;
        this.turkce = turkce;
        this.ingilizce = ingilizce;
        this.context = context;


    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater lyt = context.getLayoutInflater();
        View listeleman = lyt.inflate(R.layout.sablon, null, true);

        TextView txAd = (TextView) listeleman.findViewById(R.id.txtAd);
        TextView txTr = (TextView) listeleman.findViewById(R.id.txtTr);
        TextView txEng = (TextView) listeleman.findViewById(R.id.txtEng);

        txAd.setText(id.get(position).toString());
        txTr.setText(turkce.get(position));
        txEng.setText(ingilizce.get(position));
        return listeleman;
    }
}
