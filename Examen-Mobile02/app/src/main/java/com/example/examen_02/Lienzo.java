package com.example.examen_02;


import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class Lienzo extends View {
    private List<Circulo> contenido = new ArrayList<Circulo>();
    private String resultado = "";
    private Context nuevoC;
    private Paint paint2;
    private String password = "123";
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private Path lineas;
    private boolean sw=true;

    public Lienzo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        nuevoC = context;
        lineas = new Path();
        contenido.add(new Circulo(1,200,400,"blue"));
        contenido.add(new Circulo(2,550,400,"blue"));
        contenido.add(new Circulo(3,850,400,"blue"));
        contenido.add(new Circulo(4,200,700,"blue"));
        contenido.add(new Circulo(5,550,700,"blue"));
        contenido.add(new Circulo(6,850,700,"blue"));
        contenido.add(new Circulo(7,200,1000,"blue"));
        contenido.add(new Circulo(8,550,1000,"blue"));
        contenido.add(new Circulo(9,850,1000,"blue"));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        for (Circulo cir: contenido) {
            cir.draw(canvas,paint);
        }
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        canvas.drawLine(startX, startY, endX, endY, paint);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(50);
        paint2 = new Paint();
        paint2.setStrokeWidth(20);
        paint2.setARGB(255,255,0,0);
        paint2.setStyle(Paint.Style.STROKE);
        canvas.drawPath(lineas,paint2);
    }
    public void acceso(){
        if(password.equals(resultado) ){
            Toast.makeText(nuevoC,"CONTRASEÑA CORRECTA",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(nuevoC,SecondActivity.class);
            nuevoC.startActivity(intent);
        }else{
            Toast.makeText(nuevoC,"INTENTA DENUEVO",Toast.LENGTH_SHORT).show();
        }
        resultado="";
        sw = true;
        lineas = new Path();
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

                for (Circulo circulo: contenido) {
                    if(circulo.inside((int)endX,(int)endY) && circulo.getBandera()){
                        circulo.bandera = false;
                        circulo.color = "red";

                        startX = circulo.getCenterX();
                        startY = circulo.getCenterY();
                        if(sw){
                            lineas.moveTo(startX,startY);
                            sw = false;
                        }else{
                            lineas.lineTo(startX,startY);

                        }
                        resultado+=circulo.numero();

                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                endX = startX;
                endY = startY;
                lineas.close();
                invalidate();
                for (Circulo circulo: contenido) {
                        circulo.bandera = true;
                        circulo.color = "blue";
                }
                acceso();
                break;
        }
        return true;
    }
}