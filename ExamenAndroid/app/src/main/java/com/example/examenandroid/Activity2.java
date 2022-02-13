package com.example.examenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23,t24,t25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        t1=(TextView)findViewById(R.id.a3t1);
        t2=(TextView)findViewById(R.id.a3t2);
        t3=(TextView)findViewById(R.id.a3t3);
        t4=(TextView)findViewById(R.id.a3t4);
        t5=(TextView)findViewById(R.id.a3t5);
        t6=(TextView)findViewById(R.id.a3t6);
        t7=(TextView)findViewById(R.id.a3t7);
        t8=(TextView)findViewById(R.id.a3t8);
        t9=(TextView)findViewById(R.id.a3t9);
        t10=(TextView)findViewById(R.id.a3t10);
        t11=(TextView)findViewById(R.id.a3t11);
        t12=(TextView)findViewById(R.id.a3t12);
        t13=(TextView)findViewById(R.id.a3t13);
        t14=(TextView)findViewById(R.id.a3t14);
        t15=(TextView)findViewById(R.id.a3t15);
        t16=(TextView)findViewById(R.id.a3t16);



        super.onCreate(savedInstanceState);

        int n = getIntent().getIntExtra("n",0);

        switch(n){
            case 3:
                setContentView(R.layout.activity_2);

                int a = 1;

                int[][] numeros = new int[3][3];


                int x=1;
                int y=0;


                for(int i=1;i<=9;i++){
                    System.out.println("Prueba");
                    if(y<=-1){
                        y=2;
                    }
                    if(x>=3){
                        x=0;
                    }
                    if(numeros[x][y]==0){
                        y++;
                    }
                    numeros[x][y]=i;
                    System.out.println(numeros[x][y]);
                    x++;
                    y--;
                }

                for(int j = 0;j<=2;j++){
                    for(int i = 0;i<=2;i++){
                        System.out.println(numeros[j][i]);
                    }
                }

    /*

                t1.setText(numeros[0][0]);
                t2.setText(numeros[1][0]);
                t3.setText(numeros[2][0]);
                t4.setText(numeros[0][1]);
                t5.setText(numeros[1][1]);
                t6.setText(numeros[2][1]);
                t7.setText(numeros[0][2]);
                t8.setText(numeros[1][2]);
                t9.setText(numeros[2][2]);

                t10.setText("10");
                t11.setText("10");
                t12.setText("10");
                t13.setText("10");
                t14.setText("10");
                t15.setText("10");
                t16.setText("10");

*/
                b1 = (Button)findViewById(R.id.button2);
                b2 = (Button)findViewById(R.id.button3);

                b1.setOnClickListener(this);
                b2.setOnClickListener(this);

                break;
            case 4:
                setContentView(R.layout.activity_3);





                break;
            case 5:
                setContentView(R.layout.activity_4);



                break;
        }



    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id) {
            case R.id.button2:
                Intent intentPrimos = new Intent(this, MainActivity.class);
                this.startActivity(intentPrimos);
                break;
            case R.id.button3:
                this.
                break;
        }
    }
}
