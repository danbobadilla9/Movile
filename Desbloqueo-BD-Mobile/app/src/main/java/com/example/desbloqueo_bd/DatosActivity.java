package com.example.desbloqueo_bd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.content.ContentValues;
import android.database.Cursor;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class DatosActivity extends Activity {
    EditText jetI, jetN;
    Button jbnA, jbnL,jbnD,jbnU;
    TextView jtvL;
    SQLiteDatabase sqld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        jetI = (EditText) findViewById(R.id.xetI);
        jetN = (EditText) findViewById(R.id.xetN);
        jbnA = (Button) findViewById(R.id.xbnA);
        jbnL = (Button) findViewById(R.id.xbnL);
        jbnD = (Button) findViewById(R.id.xbnD);
        jtvL = (TextView) findViewById(R.id.xtvL);
        DbmsSQLiteHelper dsqlh = new DbmsSQLiteHelper(this, "DBUsuarios", null, 1);
        sqld = dsqlh.getWritableDatabase();
        jbnA.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String id = jetI.getText().toString();
                String nombre = jetN.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put("nombre", id);
                cv.put("valor", nombre);
                sqld.insert("Usuarios", null, cv);
                jetI.setText(""); jetN.setText("");
                list();
            }
        });
        jbnL.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        });
        //DELETE
        jbnD.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = jetI.getText().toString();
                sqld.delete("Usuarios","nombre=?",new String[]{nombre});
                jetI.setText("");
                jetN.setText("");
                list();
            }
        });

    }

    public void list(){
        String id, nombre;
        Cursor c = sqld.rawQuery("SELECT nombre,valor FROM Usuarios", null);
        jtvL.setText("");
        if (c.moveToFirst()) {
            do {
                id = c.getString(0);
                nombre = c.getString(1);
                jtvL.append(" " + id + "                         " + nombre + "\n");
            } while(c.moveToNext());
        }
        c.close();
    }
}