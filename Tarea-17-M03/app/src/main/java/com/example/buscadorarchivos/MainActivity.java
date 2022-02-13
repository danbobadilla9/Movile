package com.example.buscadorarchivos;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    Button button2;
    Button button3;
    VideoView videoView;
    MediaPlayer mediaPlayer;
    Uri path;
    String full;
    public static final int PICK_FILE =99;
    public static final int PICK_FILE2 =98;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.buscar);
        button2 = (Button) findViewById(R.id.buscar2);
        button3 = (Button) findViewById(R.id.buscar3);
        videoView = (VideoView) findViewById(R.id.videito);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.buscar){
        cargarImagen();
        }
        if(view.getId() == R.id.buscar3){
            cargarVideo();
        }
        if(view.getId() == R.id.buscar2){
            play();
        }
    }

    private void play() {
        mediaPlayer.start();
    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("audio/*");
        startActivityForResult(intent, PICK_FILE);
    }
    private void cargarVideo() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("video/*");
        startActivityForResult(intent, PICK_FILE2);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE && resultCode == RESULT_OK){
            if (data != null){
                Uri uri = data.getData();
                createMediaPlayer(uri);
            }
        }
        if (requestCode == PICK_FILE2 && resultCode == RESULT_OK){
            if (data != null){
                Uri uri = data.getData();
                Log.d("PRUEBA","ACA");
                videoView.setVideoURI(uri);
                videoView.start();
            }
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void createMediaPlayer(Uri uri){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        try {
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepare();
        } catch (IOException e){
        }
    }

}