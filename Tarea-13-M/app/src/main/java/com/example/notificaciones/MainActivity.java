package com.example.notificaciones;

import android.os.Build;
import android.os.Bundle;
import android.app.*;
import android.content.*;
import android.os.Handler;
import android.service.notification.StatusBarNotification;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int t=200, i=0;
    boolean c=true;
    TextView jtv;
    Button jbnN;
    private NotificationManager manager;
    private static final int NOTIF_ALERTA_ID = 1;
    //CONTROL//
    Handler timerHandler = new Handler();
    long startTime = 0;
    @RequiresApi(api = Build.VERSION_CODES.M)
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            if(i>0){
                StatusBarNotification[] active = manager.getActiveNotifications();
                if(active.length == 0 && null!= active){
                    i = 0;
                    jtv.setText("Cuenta: i=" + i);
                }
            }

            timerHandler.postDelayed(this, 500);
        }
    };
    //CONTROL//
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jtv = (TextView) findViewById(R.id.xtv);
        jbnN = (Button)findViewById(R.id.xbnN);
        jbnN.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View v) {
                // NotificationCompat.Builder ncb =
                // new NotificationCompat.Builder(MainActivity.this)
                // .setSmallIcon(android.R.drawable.stat_sys_warning)
                // .setLargeIcon((((BitmapDrawable) getResources()
                // .getDrawable(R.mipmap.ic_launcher)).getBitmap()))
                // .setContentTitle("Alerta de Notificación")
                // .setContentText("Uso de la notificación." + "i=" + ++i)
                // .setContentInfo("Un valor")
                // .setTicker("Mensaje de Alerta!");
                // Intent in = new Intent(MainActivity.this, MainActivity.class);
                // PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,in,0);
                // ncb.setContentIntent(pi);
                // NotificationManager nm = (NotificationManager) getSystemService(
                // Context.NOTIFICATION_SERVICE);
                // nm.notify(NOTIF_ALERTA_ID, ncb.build());
                // jtv.setText("Cuenta: i=" + i);
                // CODIGO CORREGIDO POR BOBADILLA SEGUNDO DAN ISRAEL
                Intent in = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,in,0);
                Notification.Builder nb = new Notification.Builder(getApplicationContext()).setContentTitle("Alerta de Notificación")
                        .setContentText("Uso de la notificacion. "+ "i=" + ++i).setSmallIcon(android.R.drawable.stat_notify_chat)
                        .setTicker("Mensaje de alerta")
                        .setContentIntent(pi);
                manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(1,nb.build());
                jtv.setText("Cuenta: i=" + i);
                startTime = System.currentTimeMillis();
                timerHandler.postDelayed(timerRunnable, 0);
            }
        });
//        long starttime = 0;
//        Handler h2 = new Handler();
//        Runnable run = new Runnable() {
//
//            @Override
//            public void run() {
//                long millis = System.currentTimeMillis() - starttime;
//                int seconds = (int) (millis / 1000);
//                int minutes = seconds / 60;
//                seconds     = seconds % 60;
//
//
//
//                h2.postDelayed(this, 500);
//            }
//        };
//        Timer time = new Timer();
//        time.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//
//            }
//        },0,1000);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timerHandler.removeCallbacks(timerRunnable);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}