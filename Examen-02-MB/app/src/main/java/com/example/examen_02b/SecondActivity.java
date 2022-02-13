package com.example.examen_02b;




import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private Button salir;
    private  Button reproducir;
    private WebView webView;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        salir = findViewById(R.id.Exit);
        reproducir = findViewById(R.id.Reproducir);
        salir.setOnClickListener(this);
        reproducir.setOnClickListener(this);
        videoView = findViewById(R.id.videoView);
        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.muse;
        Uri uri = Uri.parse(uriPath);
        videoView.setVideoURI(uri);
        videoView.requestFocus();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.Exit){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
        if(view.getId() == R.id.Reproducir){
            MediaController mediaController = new MediaController(this);
            videoView.setMediaController(mediaController);
            videoView.start();
        }

    }

}