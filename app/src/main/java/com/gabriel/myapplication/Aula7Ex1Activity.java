package com.gabriel.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class Aula7Ex1Activity extends AppCompatActivity {

    private TextView txtUmidade, txtTemp, txtOrvalho, txtPressao;
    private ListView listView;
    String de[] = {"temp", "umid", "press", "orv"};
    int para[] = {R.id.txtTemp, R.id.txtUmid, R.id.txtPress, R.id.txtOrv};
    List<Map<String, String>> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula7_ex1);

        txtTemp = findViewById(R.id.txtTemp);
        txtUmidade = findViewById(R.id.txtUmidade);
        txtPressao = findViewById(R.id.txtPressao);
        txtOrvalho = findViewById(R.id.txtOrvalho);
        listView = findViewById(R.id.listView);
    }

    public void onClickBuscar(View view) {
        lista = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://ghelfer.net/la/weather.json", new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String data = new String(response);
                //Toast.makeText(getApplicationContext(),data, Toast.LENGTH_SHORT).show();
                try {
                    loadData(data);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }

    private void loadData(String data) throws JSONException {


        double sumTemp = 0;
        double sumUmid = 0;
        double sumOrv = 0;
        double sumPres = 0;

        JSONObject res = new JSONObject(data);
        JSONArray array = res.getJSONArray("weather");
        for (int i = 0; i < array.length(); i++) {
            JSONObject json = array.getJSONObject(i);
            String temp = json.get("temperatura").toString();
            sumTemp += Double.parseDouble(temp);
            String umid = json.get("umidade").toString();
            sumUmid += Double.parseDouble(umid);
            String po = json.get("ponto de orvalho").toString();
            sumOrv += Double.parseDouble(po);
            String press = json.get("pressao").toString();
            sumPres += Double.parseDouble(press);

            Map<String,String> mapa= new HashMap<>();
            mapa.put("temp", temp);
            mapa.put("umid", umid);
            mapa.put( "orv", po);
            mapa.put( "press", press);
            lista.add(mapa);
        }

        txtTemp.setText(String.valueOf(sumTemp / array.length()));
        txtUmidade.setText(String.valueOf(sumUmid / array.length()));
        txtPressao.setText(String.valueOf(sumPres / array.length()));
        txtOrvalho.setText(String.valueOf(sumOrv / array.length()));

        SimpleAdapter adapter = new SimpleAdapter(this, lista, R.layout.linha_tempo, de, para);
        listView.setAdapter(adapter);


        }


    }