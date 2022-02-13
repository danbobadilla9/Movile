package com.example.tratando;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    Button button2;
    ImageView imageView;
    MediaPlayer mediaPlayer;
    Uri path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imagen);
        button = (Button) findViewById(R.id.buscar);
        button2 = (Button) findViewById(R.id.buscar2);
        button.setOnClickListener(this);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String full = RealPathUtil.getRealPath(getApplicationContext(),path);
                Log.d("PRUEBA",""+full);
                play(full);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.buscar){
            cargarImagen();
        }
    }

    private void play(String full) {
            String sub = full.substring(20);
            Log.d("PRUEBA",sub);
            mediaPlayer = MediaPlayer.create(this,Uri.parse(sub));
            mediaPlayer.seekTo(0);
            mediaPlayer.start();


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        Log.d("PRUEBA","ACA");
        startActivityForResult(intent.createChooser(intent,"Selecciona"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("PRUEBA","ENTRO");
        if(resultCode == RESULT_OK){
            path = data.getData();


            Log.d("PRUEBA",path.toString());

        }else{
            Log.d("PRUEBA","else");
        }
    }
//    AAAAAAAAAAAAAA


}