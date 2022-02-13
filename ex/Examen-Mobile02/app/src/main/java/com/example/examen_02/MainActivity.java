package com.example.examen_02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Conectar conectar;
    private Button patron;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        patron = findViewById(R.id.nuevo);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }
    public void onAttach(@NonNull Context context) {
        try {
            conectar = (Conectar) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString());
        }
    }
    public  interface Conectar {
        void sendData(boolean valor);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.nuevo){
            conectar.sendData(false);
        }
    }
}
