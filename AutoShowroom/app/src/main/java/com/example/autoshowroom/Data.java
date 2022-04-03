package com.example.autoshowroom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Data extends SQLiteOpenHelper {

    public Data(Context context){
        super(context,"Araclar",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table araclarim (arac_id INTEGER PRIMARY KEY AUTOINCREMENT,arac_marka TEXT NOT NULL,arac_model TEXT NOT NULL,arac_fiyat TEXT NOT NULL,arac_vites TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table If Exists araclarim");
        this.onCreate(db);
    }
}
