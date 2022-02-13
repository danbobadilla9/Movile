package com.example.listviewuniverso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> names;
    private List<Integer> imagenes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        //Datos que vamos a enviar
        names = new ArrayList<String>();
        //RELLENANDO DATOS
        names.add("Sol");
        names.add("Mercurio");
        names.add("Venus");
        names.add("Tierra");
        names.add("Marte");
        names.add("Jupiter");
        names.add("Saturno");
        names.add("Urano");
        names.add("Neptuno");
        names.add("Pluton");
        imagenes = new ArrayList<Integer>();
        imagenes.add(R.drawable.sol);
        imagenes.add(R.drawable.mercurio);
        imagenes.add(R.drawable.venus);
        imagenes.add(R.drawable.tierra);
        imagenes.add(R.drawable.marte);
        imagenes.add(R.drawable.jupiter);
        imagenes.add(R.drawable.saturno);
        imagenes.add(R.drawable.urano);
        imagenes.add(R.drawable.neptuno);
        imagenes.add(R.drawable.pluton);
        //Creamos el adaptador , que es la forma de renderizar, y se le pasa el layout donde se va a rellenar
        //El siguiente es un layout por defecto
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,names);
        //Le enviamos el adaptador con nuestra  informacion que necesitamos
//        listView.setAdapter(arrayAdapter);

        //AÑADIENDO EL EVENTO DE CLICK
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,"CLICK ALV"+names.get(position),Toast.LENGTH_LONG).show();
//            }
//        });
        MyAdapter myAdapter = new MyAdapter(this,R.layout.list_item,names,imagenes);
        listView.setAdapter(myAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"CLICK ALV"+names.get(position),Toast.LENGTH_LONG).show();
            }
        });
    }
}

