package com.gabriel.myapplication;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Aula10Activity extends AppCompatActivity {

    EditText etUser, etPassword;
    EditText etUser1, etPassword1, etPassword2;
    TextView tvSessao;

    @SuppressLint("MissingInflatedId") //provavelmente vai ter um erro aqui, corrigir
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula10);

         etUser = findViewById(R.id.etUser);
         etPassword = findViewById(R.id.etPassword);
         etUser1 = findViewById(R.id.etUser1);
         etPassword1 = findViewById(R.id.etPassword1);
         etPassword2 = findViewById(R.id.etPassword2);
         tvSessao = findViewById(R.id.tvSessao);



    }

    public void loginClick(View view) {
        String user = etUser.getText().toString();
        String Password = etPassword.getText().toString();

        if (user.isEmpty() || Password.isEmpty()) {
            return;
        }

        SharedPreferences settings = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String userSettings = settings.getString("User", "");
        String passSettings = settings.getString("Password", "");
        int session = settings.getInt("session", 0);

        if (user.equals(userSettings) || Password.equals(passSettings)) {
            setContentView(R.layout.activity_aula10);
            session++;
            tvSessao.setText("Sessao #" + session);
        }
    }

    public void gravarClick(View view) {
        String user1 = etUser1.getText().toString();
        String Password1 = etPassword1.getText().toString();
        String Password2 = etPassword2.getText().toString();
    }

    public void logoutClick(View view) {
    }
}
