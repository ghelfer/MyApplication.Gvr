package com.gabriel.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Aula2Ex1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula2_ex1);
        Intent intent = getIntent();
        String celsius = intent.getStringExtra("celcius");
        TextView textView3 = findViewById(R.id.textView3);
        Double cels = Double.parseDouble(celsius);
        Double farh = (cels*9/5) + 32;
        textView3.setText(String.valueOf(farh));
    }
}