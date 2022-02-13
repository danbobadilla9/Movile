package com.example.medir_rpm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ScrollView scrollView;
    private Button encender;
    private Button conectar;
    private EditText name;
    private TextView dispositivo;
    private TextView datos;
    private ProgressBar progressBar;
    private BluetoothAdapter mBlueAdapter;
    private BluetoothSocket btSocket = null;
    private Set<BluetoothDevice> pairedDeviceList;
    private String address,anterior;
    private IniciarEscucha iniciarEscucha;
    private boolean bandera = false;
    private UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        encender = findViewById(R.id.Encender);
        conectar = findViewById(R.id.Conectar);
        name = findViewById(R.id.NameDispositivo);
        dispositivo = findViewById(R.id.tipoHC);
        datos = findViewById(R.id.datos);
        scrollView = findViewById(R.id.scroll);
        datos.setMovementMethod(new ScrollingMovementMethod());
        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();//Adaptador

        encender.setOnClickListener(this);
        conectar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.Encender){
            mostrarAlerta();
            conectar.setVisibility(View.VISIBLE);
            name.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent,0);
        }
        if(view.getId() == R.id.Conectar){
            encender.setVisibility(View.INVISIBLE);
            String name = "Conectado a: "+this.name.getText();
            conectarDispositivo(this.name.getText().toString());//Hallando el dispositivo seleccionado

            this.name.setVisibility(View.GONE);
            conectar.setVisibility(View.GONE);
            dispositivo.setVisibility(View.VISIBLE);
            dispositivo.setText(name);
            bandera = true;
            onResume();
            //INICIANDO HILO

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RESULT_OK){
            Toast.makeText(MainActivity.this,"Bluetooh Activado",Toast.LENGTH_SHORT).show();
        }
    }


    public void onResume() {
        super.onResume();
        if(bandera){
            creandoSocket();
            iniciarEscucha = new IniciarEscucha(btSocket);
            iniciarEscucha.start();
        }

    }

    private void conectarDispositivo(String name){
        pairedDeviceList = mBlueAdapter.getBondedDevices();
        for (BluetoothDevice pairedDevice: pairedDeviceList) {
            if(pairedDevice.getName().equals(name)){
                address = pairedDevice.getAddress();
                Log.d("PRUEBA",address);
            }
        }
    }

    private void creandoSocket(){
        do{
            BluetoothDevice device = mBlueAdapter.getRemoteDevice(address);//Guardando el dispositivo
            try{
                btSocket = device.createRfcommSocketToServiceRecord(BTMODULEUUID);
                btSocket.connect();
                if (btSocket.isConnected()){
                    Toast.makeText(MainActivity.this, "Conectado con exito", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }while(!btSocket.isConnected());
        Log.d("PRUEBA",""+btSocket.isConnected());

    }


    private void mostrarAlerta(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Funcionamiento !!!");
        builder.setMessage("Debera de tener emparejado el dispositivo HC anteriormente!!!!");
        builder.setCancelable(true);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public static boolean isNumeric(String str) {

        if (str == null || str.length() == 0) {
            return false;
        }

        try {
            Double.parseDouble(str);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            btSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //AQUI INICIO EL HILO
    private class IniciarEscucha extends Thread {
        private InputStream inputStream = null;
        public IniciarEscucha(BluetoothSocket socket){
            try{
                inputStream = socket.getInputStream();
                inputStream.skip(inputStream.available());
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            super.run();

            try{
                while(true){
                    byte [] buffer = new byte[256];

                    if(inputStream.available() > 0){
                        progressBar.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                        inputStream.read(buffer);
                        String string = new String(buffer);
                        String  aux = "";
                        for(int i=0; i<string.length();i++){
                            if(i >= 4){
                                continue;
                            }
                            aux+=String.valueOf(string.charAt(i));

                        }
                        Log.d("PRUEBA","aux "+aux);
                        final String string2;
                        if(isNumeric(aux)){
                             string2 = aux;
                        }else{
                            string2 = "0";
                        }
                        Log.d("PRUEBA", "String final " + string2);
                        datos.post(new Runnable() {
                            @Override
                            public void run() {
                                datos.append(string2+" RPM\n");
                                scrollView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        scrollView.fullScroll(View.FOCUS_DOWN);
                                    }
                                });
                            }
                        });

                    }
                    Thread.sleep(2000);
                }
            }catch (IOException | InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}
