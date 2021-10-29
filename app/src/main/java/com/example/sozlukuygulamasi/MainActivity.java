package com.example.sozlukuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ayar=(Button) findViewById(R.id.ayar);
        Button tr=(Button) findViewById(R.id.tr);
        Button ing=(Button) findViewById(R.id.ing);
        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent treng = new Intent(MainActivity.this, treng.class);

                startActivity(treng);
            }
        });
        ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent engtr = new Intent(MainActivity.this, engtr.class);

                startActivity(engtr);
            }
        });
        ayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ayar = new Intent(MainActivity.this, ayar.class);

                startActivity(ayar);
            }
        });
    }
}