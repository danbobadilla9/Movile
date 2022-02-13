package com.example.examen_02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.examen_02.adapters.DatosAdapter;
import com.example.examen_02.models.Datos;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, RealmChangeListener<RealmResults<Datos>>, AdapterView.OnItemClickListener {
    private Button exit;
    private Button insert;
    private Realm realm;
    private ListView listView;
    private DatosAdapter adapter;
    private RealmResults<Datos> datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //BD
        realm = Realm.getDefaultInstance();
        datos = realm.where(Datos.class).findAll();
        datos.addChangeListener(this);
        //Adapter
        adapter = new DatosAdapter(this,datos,R.layout.list_datos_item);
        //Find
        exit = findViewById(R.id.Exit);
        insert = findViewById(R.id.Insert);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);




        //Listeners
        exit.setOnClickListener(this);
        insert.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.Exit){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
        if(view.getId() == R.id.Insert){
            showAlertDialog("Insertar","Ingresa los datos");
        }
    }
    private void showAlertDialog(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_datos,null);
        builder.setView(view);
        EditText name = view.findViewById(R.id.name);
        EditText valor = view.findViewById(R.id.valor);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String nam = name.getText().toString().trim();
                String val = valor.getText().toString().trim();
                createNewData(nam,val);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void createNewData(String nam, String val) {
        realm.beginTransaction();
        Datos datos = new Datos(nam,val);
        realm.copyToRealm(datos);
        realm.commitTransaction();
    }

    @Override
    public void onChange(RealmResults<Datos> datos) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.d("PRUEBA","Position"+datos.get(position).getId());
        realm.beginTransaction();
        datos.get(position).deleteFromRealm();
        realm.commitTransaction();
        Toast.makeText(this, "Position"+datos.get(position).getId(), Toast.LENGTH_SHORT).show();
    }
}