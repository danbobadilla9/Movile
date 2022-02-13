package com.example.tarea24;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.*;
import android.app.*;
import android.view.View;
import android.widget.*;
public class MainActivity extends Activity implements View.OnClickListener {
    SharedPreferences sp;
    EditText   jetn, jetx, jety;
    String    s;
    float  x, y;
    Button show;
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jetn = (EditText) findViewById(R.id.xetn);
        jetx = (EditText) findViewById(R.id.xetx);
        jety = (EditText) findViewById(R.id.xety);
        show = findViewById(R.id.show);
        sp = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        s = sp.getString("titulo", "ESCOM");
        x = sp.getFloat("x", 0);
        y = sp.getFloat("y", 0);
        jetn.setText(s);
        jetx.setText("" + x);
        jety.setText("" + y);
        show.setOnClickListener(this);
    }
    protected void onPause(){
        super.onPause();

    }

    @Override
    public void onClick(View view) {
        s = jetn.getText().toString();
        x = Float.parseFloat(jetx.getText().toString());
        y = Float.parseFloat(jety.getText().toString());
        SharedPreferences.Editor miEditor = sp.edit();
        miEditor.putString("titulo", s);
        miEditor.putFloat("x", x);
        miEditor.putFloat("y", y);
        miEditor.commit();
        Toast.makeText(this, "Preferencias guardadas", Toast.LENGTH_LONG).show();
        Intent intent= new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
