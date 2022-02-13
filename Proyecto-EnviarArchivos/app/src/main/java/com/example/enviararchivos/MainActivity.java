package com.example.enviararchivos;
import static java.security.AccessController.getContext;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.net.UnknownHostException;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Socket cliente,aux;
    private ServerSocket serverSoket;
    private PrintWriter printWriter;
    private EditText Ip;
    private EditText Msg;
    private Button enviar,guardar;
    private Button file;
    private String menssage,menssage2;
    private TextView historial;
    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamt;
    private Handler handler = new Handler();
    private Handler handler2 = new Handler();
    private String path;
    Date myDate = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    int port = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        historial = findViewById(R.id.historial);
        file = findViewById(R.id.file);
        Ip = findViewById(R.id.IP);
        Msg = findViewById(R.id.mensaje);
        enviar = findViewById(R.id.BtnEnviar);
        enviar.setOnClickListener(this);
        file.setOnClickListener(this);
        guardar = findViewById(R.id.guardar);
        guardar.setOnClickListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSoket = new ServerSocket(8001);
                    while(true){
                        aux = serverSoket.accept();
                        byte b[] = new byte [20002];
                        InputStream is =  aux.getInputStream();
                        is.read(b,0,b.length);
                        //Inicio codigo
                        try{
                            File[] f1vTarjetasDeAlmacenamiento = ContextCompat.getExternalFilesDirs(MainActivity.this, null);
                            if (f1vTarjetasDeAlmacenamiento.length > 1 && f1vTarjetasDeAlmacenamiento[1] != null ){
                                String sDirectorio = f1vTarjetasDeAlmacenamiento[1].toString();
                                File fArchivo = new File(sDirectorio, "entrada3" + ".csv");
                                DataOutputStream oswArchivo = new DataOutputStream (new FileOutputStream(fArchivo));
                                oswArchivo.write(b,0,b.length);
                                oswArchivo.close();
                            }
                        }catch (IOException ex){
                            Toast.makeText(MainActivity.this, "Error al escribir en la tarjeta SD.", Toast.LENGTH_SHORT).show();
                            Log.e("Ficheros", "Error al escribir rn la tarjeta SD");
                            Log.d("error", ex.toString());
                        }
                        //Final Codigo

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.BtnEnviar){
            menssage = Msg.getText().toString();
            Msg.setText("");
            port = 8000;
        }else if(view.getId() == R.id.file) {
            openFileDialog();
        }else if(view.getId() == R.id.guardar){
            try{
                File[] f1vTarjetasDeAlmacenamiento = ContextCompat.getExternalFilesDirs(this, null);
                if (f1vTarjetasDeAlmacenamiento.length > 1 && f1vTarjetasDeAlmacenamiento[1] != null ){

                    String sDirectorio = f1vTarjetasDeAlmacenamiento[1].toString();
                    File fArchivo = new File(sDirectorio, "entrada2" + ".csv");
                    OutputStreamWriter oswArchivo = new OutputStreamWriter(new FileOutputStream(fArchivo));
                    oswArchivo.write("sContenido");
                    oswArchivo.close();
                    Toast.makeText(this, "Archivo guardado en SD: " + f1vTarjetasDeAlmacenamiento[1].toString(), Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(this, "No se reconoce la tarjeta SD o no está insertada", Toast.LENGTH_SHORT).show();
                }
            }catch (IOException ex){
                Toast.makeText(this, "Error al escribir en la tarjeta SD.", Toast.LENGTH_SHORT).show();
                Log.e("Ficheros", "Error al escribir rn la tarjeta SD");
            }
        }
    }
    ActivityResultLauncher<Intent> sActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        Uri uri = data.getData();
                        Toast.makeText(MainActivity.this, Environment.getExternalStorageDirectory().toString()+"/forensics/datos/enviar.csv", Toast.LENGTH_SHORT).show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    cliente = new Socket(Ip.getText().toString(),8000);
                                    InputStream in =  getContentResolver().openInputStream(uri);
                                    OutputStream out = cliente.getOutputStream();
                                    byte[] buf = new byte[2024];
                                    int len;
                                    while((len=in.read(buf))>0){
                                        out.write(buf,0,len);
                                    }
                                    out.close();
                                    in.close();
                                    cliente.close();
                                }catch (IOException e){
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                }
            }
    );
    public void openFileDialog(){
        Intent data = new Intent(Intent.ACTION_GET_CONTENT);
        data.setType("*/*");
        data = Intent.createChooser(data,"Escoge tu fila");
        sActivityResultLauncher.launch(data);
    }
}