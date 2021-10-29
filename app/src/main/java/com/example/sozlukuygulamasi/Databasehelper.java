package com.example.sozlukuygulamasi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Databasehelper extends SQLiteOpenHelper {
    static String vtisim = "sozluk";
    static int version = 1;

    public Databasehelper(Context context) {

        super(context, vtisim, null, version);
        context.openOrCreateDatabase(vtisim, context.MODE_PRIVATE, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table kelimeler(id INTEGER primary key autoincrement, turkce text , ingilizce text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     /*   db.execSQL("drop table Site");
        this.onCreate(db);*/
    }
}