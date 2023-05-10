package com.gabriel.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void aula2Click(View view) {
        startActivity(new Intent(this,Aula2Activity.class));
    }
    public void aula7Click(View view) {
        startActivity(new Intent(this,Aula7Activity.class));
    }
    public void aula10CClick(View view) {startActivity(new Intent(this,Aula10Activity.class));

    }
}