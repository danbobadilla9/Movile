package com.example.examen_02b;




import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class Lienzo extends View   {
    private List<Figuras> matriz = new ArrayList<Figuras>();
    private String resultado = "";
    private Context contextL;
    private String password = "";
    private float startX;
    private float startY;
    private float auxX;
    private float auxY;
    private float endX;
    private float endY;
    private boolean sw=true;
    // CANVAS
    private Path recta;
    private Paint paint2;
    //Botones
    private Boton boton = new Boton("Yellow");
    private boolean inicio = false;
    private boolean nuevaP = false;
    public Lienzo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        matriz.add(new Figuras(1,200,400,"blue"));
        matriz.add(new Figuras(2,550,400,"blue"));
        matriz.add(new Figuras(3,850,400,"blue"));
        matriz.add(new Figuras(4,200,700,"blue"));
        matriz.add(new Figuras(5,550,700,"blue"));
        matriz.add(new Figuras(6,850,700,"blue"));
        matriz.add(new Figuras(7,200,1000,"blue"));
        matriz.add(new Figuras(8,550,1000,"blue"));
        matriz.add(new Figuras(9,850,1000,"blue"));
        recta = new Path();
        contextL = context;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Inicializando el pincel
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.CYAN);
        canvas.drawPaint(paint);
        //Dibujando El boton de patron
        boton.draw(canvas,paint);
        //Pintando la matriz de circulos
        for (Figuras circulos: matriz) {
            circulos.draw(canvas,paint);
        }
        //Cambiando de color al pincel
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        //Dibujando en el dedo
        canvas.drawLine(startX, startY, endX, endY, paint);

        //Instanciando la clase paint de la linea entre las figuras
        paint2 = new Paint();
        paint2.setStrokeWidth(20);
        paint2.setARGB(255,255,0,0);
        paint2.setStyle(Paint.Style.STROKE);
        //Dibujando las lineas con Path
        canvas.drawPath(recta,paint2);
    }
    public void acceso(){
        if(password.equals(resultado) ){
            Toast.makeText(contextL,"CONTRASEÑA CORRECTA",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(contextL,SecondActivity.class);
            contextL.startActivity(intent);
        }else{
            Toast.makeText(contextL,"INTENTA DENUEVO",Toast.LENGTH_SHORT).show();
        }
        resultado="";
        sw = true;
        recta = new Path();
    }
    public void setReinicio(){
        Log.d("PRUEBA","ENTRO");
        boton.texto = "Ingresa Un Nuevo Patron";
        inicio = false;
        password = "";
        resultado = "";
        sw = true;
        recta = new Path();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                endX = event.getX();
                endY = event.getY();

                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                endX = event.getX();
                endY = event.getY();

                    Log.d("PRUEBA","EN EL BOTON: "+boton.inside((int)endX,(int)endY));

                for (Figuras circulo: matriz) {
                    if(circulo.inside((int)endX,(int)endY) && circulo.getBandera()){
                        circulo.bandera = false;
                        circulo.color = "red";

                        startX = circulo.getCenterX();
                        startY = circulo.getCenterY();
                        if(sw){
                            recta.moveTo(startX,startY);
                            sw = false;
                        }else{
                            recta.lineTo(startX,startY);

                        }
                        resultado+=circulo.getNumero();

                    }
                }

                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                auxX = endX;
                auxY = endY;
                endX = startX;
                endY = startY;
                recta.close();
                invalidate();
                if(boton.inside((int)auxX,(int)auxY)){
                    setReinicio();
                }else if(inicio == false){
                    inicio = true;
                    password = resultado;
                    resultado="";
                    sw = true;
                    for (Figuras circulo: matriz) {
                        circulo.bandera = true;
                        circulo.color = "blue";

                    }
                    recta = new Path();
                    boton.texto = "Ingresa La Contraseña";
                }else{
                    for (Figuras circulo: matriz) {
                        circulo.bandera = true;
                        circulo.color = "blue";
                    }
                    acceso();
                }

                break;
        }
        return true;
    }


}
