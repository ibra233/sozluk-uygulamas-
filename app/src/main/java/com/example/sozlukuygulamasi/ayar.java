package com.example.sozlukuygulamasi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ayar extends AppCompatActivity {
    int guncellemeid=-1;
    ListView sozlukliste;
    db_islem dbislem = new db_islem(this);
    ArrayList<Integer> mid = new ArrayList<>();
    ArrayList<String> turkce = new ArrayList<>();
    ArrayList<String> ingilizce = new ArrayList<>();
    static SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayar);
        sozlukliste = (ListView) findViewById(R.id.listview);
        Button geri=(Button) findViewById(R.id.buttongeri);
        getWord();

        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(ayar.this, MainActivity.class);

                startActivity(main);
            }
        });
        sozlukliste.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ayar.this);
                String[] tercih={"Düzenle","Sil"};
                builder.setItems(tercih, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:{
                            guncellemeid=mid.get(position);
                                EditText turkceedit=(EditText) findViewById(R.id.editTurkish);
                                EditText ingilizceedit=(EditText) findViewById(R.id.editEnglish);
                                turkceedit.setText(turkce.get(position));
                                ingilizceedit.setText(ingilizce.get(position));
                                CharSequence text = "Güncellenecek veri seçildi";
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                                toast.show();
                            }
                            break;
                            case 1:{

                              delete(mid.get(position));
                              mid.clear();
                              turkce.clear();
                              ingilizce.clear();
                             getWord();

                            }
                            break;
                            default:
                                break;
                        }
                    }
                });
                builder.show();
                return true;
            }
        });

    }
    public void getWord(){


        Cursor cs = dbislem.VerileriAl();
        while (cs.moveToNext()) {
            mid.add(cs.getInt(0));
            turkce.add(cs.getString(1));
            ingilizce.add(cs.getString(2));

        }

        MyAdapter my = new MyAdapter(this, mid, turkce, ingilizce);
        sozlukliste.setAdapter(my);
    }

public void delete(int id){
    ContentValues delete=new ContentValues();
    delete.put("id",id);
   int basari= dbislem.VeriSil(delete);
   String a=String.valueOf(basari);
   if(basari>0){
       CharSequence text = "Veri Silindi!";
       int duration = Toast.LENGTH_SHORT;

       Toast toast = Toast.makeText(this, text, duration);
       toast.show();
   }else{
       CharSequence text = a;
       int duration = Toast.LENGTH_SHORT;

       Toast toast = Toast.makeText(this, text, duration);
       toast.show();
   }
}

    public  void kayit(View v){

        EditText turkceedit=(EditText) findViewById(R.id.editTurkish);
        EditText ingilizceedit=(EditText) findViewById(R.id.editEnglish);

        ContentValues add=new ContentValues();

        add.put("turkce",turkceedit.getText().toString());
        add.put("ingilizce",ingilizceedit.getText().toString());
        boolean getir=dbislem.VeriEkle(add);

        if (getir==true){

            CharSequence text = "Veri Eklendit!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
            mid.clear();
            turkce.clear();
            ingilizce.clear();
            getWord();
        }


    }
    public void guncelle(View v){
if(guncellemeid==-1){
    CharSequence text = "Güncellenecek kelimeleri seçiniz";
    int duration = Toast.LENGTH_SHORT;

    Toast toast = Toast.makeText(this, text, duration);
    toast.show();
}else{

    EditText turkceedit=(EditText) findViewById(R.id.editTurkish);
    EditText ingilizceedit=(EditText) findViewById(R.id.editEnglish);
    ContentValues update=new ContentValues();
    update.put("id",guncellemeid);
   update.put("turkce",turkceedit.getText().toString());
   update.put("ingilizce",ingilizceedit.getText().toString());
int basari=dbislem.VeriGuncelle(update);
    CharSequence text = "Kelimeler başarıyla güncellendi" ;
    int duration = Toast.LENGTH_SHORT;

    Toast toast = Toast.makeText(this, text, duration);
    toast.show();
    mid.clear();
    turkce.clear();
    ingilizce.clear();
    getWord();
    turkceedit.setText("");
    ingilizceedit.setText("");
}
    }


}
