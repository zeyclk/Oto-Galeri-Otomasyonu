package com.example.autoshowroom;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.DecimalFormat;

public class AracKayit extends AppCompatActivity {

    Spinner marka;
    EditText model1,fiyat1;
    RadioButton oto,man;
    Button kaydetme;
    Data vt;
    String[] markalar={"Mercedes","Land Rover","Audi","Honda"};
    ArrayAdapter adp;
    String secilen="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_kayit);

        vt=new Data(this);
        marka=findViewById(R.id.sp_mark);
        model1=findViewById(R.id.edt_model);
        fiyat1=findViewById(R.id.edt_fiyat);
        kaydetme=findViewById(R.id.btn_giris);
        oto=findViewById(R.id.rd_oto);
        man=findViewById(R.id.rd_manu);

        adp=new ArrayAdapter(this, android.support.constraint.R.layout.support_simple_spinner_dropdown_item,markalar);
        marka.setAdapter(adp);

        marka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                secilen=marka.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(),"Marka seçilmedi",Toast.LENGTH_SHORT).show();
            }
        });

        kaydetme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(secilen!="" && model1.getText().toString()!="" && fiyat1.getText().toString()!="") {
                    SQLiteDatabase db = vt.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put("arac_marka", secilen.toString());
                    cv.put("arac_model", model1.getText().toString());
                    cv.put("arac_fiyat", fiyat1.getText().toString());
                    if (oto.isChecked()) cv.put("arac_vites", oto.getText().toString());
                    else cv.put("arac_vites", man.getText().toString());
                    db.insert("araclarim", null, cv);

                    Toast.makeText(getApplicationContext(),"Kayıt başarılı",Toast.LENGTH_LONG).show();
                    Intent m=new Intent(AracKayit.this,TumAraclar.class);
                    startActivity(m);

                }

                else Toast.makeText(getApplicationContext(),"Alanların hepsini doldumanız gerekiyor",Toast.LENGTH_SHORT).show();


            }
        });
    }
}