package com.example.autoshowroom;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;

import java.util.ArrayList;

public class TumAraclar extends AppCompatActivity {
    ListView liste;
    Data vt;
    Button anamenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tum_araclar);

        vt=new Data(this);
        liste=findViewById(R.id.lv_kayit);
        anamenu=findViewById(R.id.btn_menu);

        String[] sutunlar={"arac_id","arac_marka","arac_model"};
        SQLiteDatabase reader=vt.getReadableDatabase();
        Cursor cursor=reader.query("araclarim",sutunlar,null,null,null,null,null);
        ArrayList<String> lv=new ArrayList<String>();

        while (cursor.moveToNext())
        {
            lv.add(cursor.getString(1)+" "+cursor.getString(2));
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,lv);
        liste.setAdapter(adapter);

        anamenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent z=new Intent(TumAraclar.this,MainActivity.class);
                startActivity(z);
            }
        });
    }
}