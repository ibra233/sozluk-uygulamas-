package com.example.sozlukuygulamasi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class db_islem {
    final Context context;

    db_islem(Context cn) {
        context = cn;
    }

    public SQLiteDatabase dbgetir(boolean durum) {
        Databasehelper dbhelper = new Databasehelper(context);
        if (durum)
            return dbhelper.getWritableDatabase();
        else
            return dbhelper.getReadableDatabase();
    }

    public boolean VeriEkle(ContentValues cnt) {
        try {
            SQLiteDatabase db = dbgetir(true);
            long newRowId = db.insert("kelimeler", null, cnt);
            return newRowId != -1 ? true : false;
        } catch (SQLiteException ex) {
            return false;
        }
    }
   public Cursor likeSorgu(String dil,String like){
       try {
           SQLiteDatabase db = dbgetir(false);
           Cursor cs = db.rawQuery("Select * from kelimeler where "+dil+" LIKE '" + like + "%'", null);

           return cs;

       } catch (SQLiteException ex) {

           return null;
       }
    }
    public int VeriSil(ContentValues delete) {
        SQLiteDatabase db = dbgetir(true);
        int did=(int)delete.get("id");

        int deleteRowId=db.delete("kelimeler","id"+" ="+did,null);

        return deleteRowId;
    }
    public int VeriGuncelle(ContentValues update) {
        SQLiteDatabase db = dbgetir(true);
        int id=(int)update.get("id");

        int updateId=db.update("kelimeler",update,"id ="+id,null);

        return updateId;
    }

    public Cursor VerileriAl() {
        try {
            SQLiteDatabase db = dbgetir(false);
            Cursor cs = db.rawQuery("Select * from kelimeler", null);

            return cs;

        } catch (SQLiteException ex) {

            return null;
        }

    }

}