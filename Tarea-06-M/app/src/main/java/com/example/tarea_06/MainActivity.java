package com.example.tarea_06;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
public class MainActivity extends Activity { // ServiceTimerActivity
    private TextView jtv;
    private Button jbn;
    private boolean click = true;
    private boolean click2 = true;
    private double control;
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jtv = (TextView) findViewById(R.id.xtvT);
        jbn = (Button) findViewById(R.id.xbnI);
        Button stopButton = (Button) findViewById(R.id.xbnT);
        jbn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopButton.setVisibility(View.VISIBLE);
                if(click){
                    jbn.setText("Terminar");
                    stopButton.setText("Pause");
                    click = false;
                    MiCrono.continuarCrono(0);
                    initCrono();
                }else{
                    stopCrono();
                    stopButton.setVisibility(View.GONE);
                    click = true;
                    jbn.setText("Comenzar");
                }
                //Llamamos al metodo de abajo
            }
        });
        stopButton.setVisibility(View.GONE);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(click2){
                    click2 = false;
                    stopCrono();
                    stopButton.setText("Play");
                }else{
                    MiCrono.continuarCrono(control);
                    click2 = true;
                    initCrono();
                    stopButton.setText("Pause");
                }
                //Llamamos al metodo para parar

            }
        });
        //Llamada al metodo estatico, donde le pasamos el contexto
        MiCrono.setUpdateListener(this);
    }
    //Detiene el cronometro cuando la aplicación esta apunto se ser destruida
    @Override
    protected void onDestroy() {
        stopCrono();
        super.onDestroy();
    }
    private void initCrono() {
        Intent in = new Intent(this, MiCrono.class);
        startService(in);
    }
    private void stopCrono() {
        Intent in = new Intent(this, MiCrono.class);
        stopService(in);
    }
    public void refreshCrono(double t) {
        control = t;
        jtv.setText(String.format("%.2f", t) + " segs");
    }
}