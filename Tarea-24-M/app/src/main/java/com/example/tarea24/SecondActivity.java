package com.example.tarea24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView longitud,latitud;
    SharedPreferences sp;
    float  x, y;
    String    s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        longitud = findViewById(R.id.lonigtudS);
        latitud = findViewById(R.id.latitudS);
        sp = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        s = sp.getString("titulo", "ESCOM");
        x = sp.getFloat("x", 0);
        y = sp.getFloat("y", 0);
        latitud.setText(String.valueOf(x));
        longitud.setText(String.valueOf(y));
    }
}