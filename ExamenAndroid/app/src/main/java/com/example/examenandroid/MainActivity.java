package com.example.examenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button boton;
    EditText numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button)findViewById(R.id.button);
        numero = (EditText)findViewById(R.id.editText);

        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int n = Integer.parseInt(numero.getText().toString());

        if(n==3 || n==5 || n==4){
            Intent intent = new Intent(this,Activity2.class);
            intent.putExtra("n",n);
            this.startActivity(intent);
        }else{
            Toast.makeText(this, "Ingrese un numero valido", Toast.LENGTH_SHORT).show();
        }



    }
}
