package com.example.ping_pong;

import static com.example.ping_pong.GameView.mover;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = findViewById(R.id.leftt);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mover(KeyEvent.KEYCODE_DPAD_LEFT);
            }
        });
    }
}