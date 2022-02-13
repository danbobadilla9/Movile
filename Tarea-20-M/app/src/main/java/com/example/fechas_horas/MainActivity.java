package com.example.fechas_horas;

import android.app.*;
import android.content.ContentValues;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import java.util.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends Activity implements OnClickListener {
    Button jbnF, jbnH;
    EditText txtDate, txtTime;
    EditText name;
    int a, m, d, h, n;
    Button jbnA, jbnL;
    TextView jtvL;
    SQLiteDatabase sqld;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jbnF = (Button)findViewById(R.id.xbnF); jbnF.setOnClickListener(this);
        jbnH = (Button)findViewById(R.id.xbnH); jbnH.setOnClickListener(this);
        jbnA = (Button) findViewById(R.id.xbnA);
        jbnL = (Button) findViewById(R.id.xbnL);
        jtvL = (TextView) findViewById(R.id.xtvL);

        name = findViewById(R.id.nombre);
        txtDate = (EditText)findViewById(R.id.xetF);
        txtTime = (EditText)findViewById(R.id.xetH);
        DbmsSQLiteHelper dsqlh = new DbmsSQLiteHelper(this, "DBContactos", null, 1);
        sqld = dsqlh.getWritableDatabase();
        jbnA.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String nombre = name.getText().toString();
                String fecha = txtDate.getText().toString()+" "+txtTime.getText().toString();
                Log.d("PRUEBA","Nombre: "+nombre+" Fecha: "+fecha);
                ContentValues cv = new ContentValues();
                cv.put("nombre", nombre);
                cv.put("fecha", fecha);
                sqld.insert("Contactos", null, cv);
                name.setText(""); txtDate.setText("");txtTime.setText("");
            }
        });
        jbnL.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.d("PRUEBA","ACA");
                String id, nombre;
                Cursor c = sqld.rawQuery("SELECT nombre,fecha FROM Contactos", null);
                jtvL.setText("");
                Log.d("PRUEBA","ACA2");
                if (c.moveToFirst()) {
                    do {
                        id = c.getString(0);
                        nombre = c.getString(1);
                        Log.d("PRUEBA",nombre);
                        String[] fecha = nombre.split(" ");
                        jtvL.append("Nombre: " + id + " Fecha: " + fecha[0] +" Hora: "+fecha[1]+ "\n");
                    } while(c.moveToNext());
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v == jbnF) {
            Calendar c = Calendar.getInstance();
            a = c.get(Calendar.YEAR);
            m = c.get(Calendar.MONTH);
            d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dpd = new DatePickerDialog(this, new
                    DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker dp, int ye, int mo, int di) {
                            txtDate.setText(di + "-" + (mo + 1) + "-" + ye);
                        }
                    }, a, m, d);
            dpd.show();
        }
        if (v == jbnH) {
            Calendar c = Calendar.getInstance();
            h = c.get(Calendar.HOUR_OF_DAY);
            n = c.get(Calendar.MINUTE);
            TimePickerDialog tpd = new TimePickerDialog(this, new
                    TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker tp, int ho, int mi) {
                            txtTime.setText(ho + ":" + mi);
                        }
                    }, h, n, false);
            tpd.show();
        }
    }
}