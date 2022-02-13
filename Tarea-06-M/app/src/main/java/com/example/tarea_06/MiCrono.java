package com.example.tarea_06;

import java.util.*;
import android.app.Service;
import android.content.Intent;
import android.os.*;
public class MiCrono extends Service {
    private Timer t = new Timer();
    private static final long INTERVALO_ACTUALIZACION = 10; // En milisegundos
    public static MainActivity UPDATE_LISTENER;
    private static double n=0;
    private Handler h;
    //Este metodo estatico recibe el contesto y lo guarda en un objeto de tipo MainActivity
    public static void setUpdateListener(MainActivity sta) {
        UPDATE_LISTENER = sta;
    }
    public static  void continuarCrono(double continuarC){
        n = continuarC;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        iniciarCrono();
        h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                UPDATE_LISTENER.refreshCrono(n);
            }
        };
    }

    @Override
    public void onDestroy() {
        pararCrono();
        super.onDestroy();
    }
    //NUNCA SE UTILIZA ON BIND
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    private void iniciarCrono() {
        t.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                n += 0.01;
                h.sendEmptyMessage(0);
            }
        }, 0, INTERVALO_ACTUALIZACION);
    }

    private void pararCrono() {
        if (t != null)
            t.cancel();
    }
}

