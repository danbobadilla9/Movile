package com.example.chat_sockets;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
    private Button enviar;
    private String menssage,menssage2;
    private TextView historial;
    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamt;
    private Handler handler = new Handler();
    private Handler handler2 = new Handler();
    Date myDate = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    int port = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        historial = findViewById(R.id.historial);
        Ip = findViewById(R.id.IP);
        Msg = findViewById(R.id.mensaje);
        enviar = findViewById(R.id.BtnEnviar);
        enviar.setOnClickListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSoket = new ServerSocket(8001);
                    while(true){
                        aux = serverSoket.accept();
                        inputStreamt = new InputStreamReader(aux.getInputStream());
                        bufferedReader = new BufferedReader(inputStreamt);
                        menssage2 = bufferedReader.readLine();
                        handler2.post(new Runnable() {
                            @Override
                            public void run() {
                                historial.append( dateFormat.format(myDate)+" PC: "+menssage2+"\n");
                            }
                        });

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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cliente = new Socket(Ip.getText().toString(),port);
                        printWriter = new PrintWriter(cliente.getOutputStream());
                        printWriter.write(menssage);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                historial.append(dateFormat.format(myDate)+" Tú: "+menssage+"\n");
                            }
                        });

                        printWriter.flush();
                        printWriter.close();
                        cliente.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}