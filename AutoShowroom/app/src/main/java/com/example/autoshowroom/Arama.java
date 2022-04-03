package com.example.autoshowroom;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Arama extends AppCompatActivity {

    Data vt;
    Spinner aranan;
    Button ara,sil;
    ListView kayitlar;
    TextView silinecek;
    String[] m={"Mercedes","Land Rover","Audi","Honda"};
    String marka="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arama);

        vt=new Data(this);
        aranan=findViewById(R.id.sp_ara);
        ara=findViewById(R.id.btn_arama);
        sil=findViewById(R.id.btn_sil);
        sil.setEnabled(false);
        kayitlar=findViewById(R.id.lv_kayit);
        silinecek=findViewById(R.id.txt_sil);

        ArrayAdapter<String> adp2=new ArrayAdapter<String>(this, android.support.constraint.R.layout.support_simple_spinner_dropdown_item,m);
        aranan.setAdapter(adp2);

        aranan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                marka=aranan.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(),"Marka seçiniz",Toast.LENGTH_SHORT).show();
            }
        });


        ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(marka!="") {
                    String[] alanlar = {"arac_id", "arac_marka", "arac_model"};
                    SQLiteDatabase dboku = vt.getReadableDatabase();
                    Cursor cursor = dboku.query("araclarim", alanlar, "arac_marka=?",new String[]{aranan.getSelectedItem().toString()}, null, null, null);
                    ArrayList<String> liste = new ArrayList<String>();

                    while (cursor.moveToNext()) {
                        liste.add(cursor.getString(1) + " " + cursor.getString(2));
                        silinecek.setText(cursor.getString(0));
                    }
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,liste);
                    kayitlar.setAdapter(adapter);
                    sil.setEnabled(true);
                }
                else Toast.makeText(getApplicationContext(),"Aranacak markayı giriniz",Toast.LENGTH_SHORT).show();
            }
        });

        kayitlar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                sil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Arama.this);
                        builder.setTitle("Auto Showroom");
                        builder.setMessage("Aracı silmek istiyor musunuz?");
                        builder.setNegativeButton("Hayır",null);
                        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        SQLiteDatabase dbsil = vt.getWritableDatabase();
                                        Integer x=dbsil.delete("araclarim","arac_id=?",new String[]{silinecek.getText().toString()});

                                        if(x>-1)
                                        {

                                            Toast.makeText(Arama.this,"Silme başarılı",Toast.LENGTH_SHORT).show();
                                            Intent t=new Intent(Arama.this,TumAraclar.class);
                                            startActivity(t);
                                        }
                                        else Toast.makeText(Arama.this,"Silme başarılı değil",Toast.LENGTH_SHORT).show();
                                    }
                                });
                        builder.show();
                    }

                });
            }
        });


    }

}