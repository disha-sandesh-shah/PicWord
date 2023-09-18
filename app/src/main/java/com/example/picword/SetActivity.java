package com.example.picword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SetActivity extends AppCompatActivity {
    Button btnSet1;
    Button btnSet2;
    Button btnSet3;
    Button btnSet4;
    Button btnSet5;
    Button btnSet6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        btnSet1 = findViewById(R.id.btnSet1);
        btnSet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Set1Activity.class);
                startActivity(intent);
            }
        });

        btnSet2 = findViewById(R.id.btnSet2);
        btnSet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Set2Activity.class);
                startActivity(intent);
            }
        });

        btnSet3 = findViewById(R.id.btnSet3);
        btnSet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Set3Activity.class);
                startActivity(intent);
            }
        });

        btnSet4 = findViewById(R.id.btnSet4);
        btnSet4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Set4Activity.class);
                startActivity(intent);
            }
        });

        btnSet5= findViewById(R.id.btnSet5);
        btnSet5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Set5Activity.class);
                startActivity(intent);
            }
        });

        btnSet6 = findViewById(R.id.btnSet6);
        btnSet6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Set6Activity.class);
                startActivity(intent);
            }
        });
    }
}