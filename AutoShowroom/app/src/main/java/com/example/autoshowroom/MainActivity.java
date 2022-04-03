package com.example.autoshowroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button kayit,listele,ara;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kayit=findViewById(R.id.btn_kayit);
        listele=findViewById(R.id.btn_tum);
        ara=findViewById(R.id.btn_ara);

        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent g=new Intent(MainActivity.this,AracKayit.class);
                startActivity(g);
            }
        });

        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l=new Intent(MainActivity.this,TumAraclar.class);
                startActivity(l);
            }
        });

        ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(MainActivity.this,Arama.class);
                startActivity(a);
            }
        });
    }
}