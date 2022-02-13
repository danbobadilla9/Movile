package com.example.tarea_03;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.*;
import android.content.Intent;
import android.widget.*;
public class MainActivity extends Activity{
    EditText jet,jet2,jet3;
    Button jbn;
    Bundle bdl;
    Intent itn;
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jet = (EditText) findViewById(R.id.xet);
        jet2 = (EditText) findViewById(R.id.xet2);
        jet3 = (EditText) findViewById(R.id.xet3);
        jbn = (Button) findViewById(R.id.xbn);
        jbn.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                itn = new Intent(MainActivity.this, SegundaActivity.class);
                bdl = new Bundle();
                int a =Integer.parseInt(jet.getText().toString());
                int b =Integer.parseInt(jet2.getText().toString());
                int c =Integer.parseInt(jet3.getText().toString());
                if(b*b<4*a*c){
                    bdl.putString("cuadrado", "Solucion perteneciente al campo de los numeros complejos.");
                }
                else if(a!=0&&b*b>4*a*c){
                    bdl.putString("cuadrado", "La primera solucion es "+(-b+Math.sqrt(b*b-4*a*c))/(2*a)+"\n" +
                                    "La segunda solucion es"+(-b-Math.sqrt(b*b-4*a*c))/(2*a));
                }
                itn.putExtras(bdl);
                startActivity(itn);
            }
        });
    }
}
