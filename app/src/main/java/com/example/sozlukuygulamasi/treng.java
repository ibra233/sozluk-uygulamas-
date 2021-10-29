package com.example.sozlukuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class treng extends AppCompatActivity {
    ListView trlistview;
    TextView sonuc;
    db_islem dbislem = new db_islem(this);
    ArrayList<String> turkce = new ArrayList<>();
    ArrayList<String> ingilizce = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treng);

         sonuc=(TextView) findViewById(R.id.textViewsonuc);
        SearchView arama=(SearchView) findViewById(R.id.search);
        trlistview = (ListView) findViewById(R.id.trlistview);
arama.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(arama.getQuery().length() == 0){
            trlistview.setAdapter(null);
            sonuc.setText("Bulunan kelime sayısı:");
            return false;
      }
        else{
            getWord(newText);
            return true;
        }
    }
});

    }
    public void getWord(String aranacak){


        turkce.clear();
        ingilizce.clear();

        Cursor cs = dbislem.likeSorgu("turkce",aranacak);

        while (cs.moveToNext()) {

            turkce.add(cs.getString(1));
            ingilizce.add(cs.getString(2));

        }
     sonuc.setText("Bulunan kelime sayısı:"+turkce.size());
        SearchAdapter search = new SearchAdapter(this, turkce, ingilizce);
        trlistview.setAdapter(search);
    }
}