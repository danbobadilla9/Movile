package com.example.desbloqueo_deficiente;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
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
    private int posx = 0, posy = 0;
    private Context nuevoC;
    private Path path ;
    private Paint paint2;
    private List<Path> paths;
    private List<Paint> paints;
    private boolean bandera = true;
    //
    private String password = "123";
    //BANDERA 2
    private boolean bandera2 = false;
    public Lienzo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        nuevoC = context;
        paths = new ArrayList<>();
        paints = new ArrayList<>();
        contenido.add(new Circulo(1,200,400));
        contenido.add(new Circulo(2,550,400));
        contenido.add(new Circulo(3,850,400));
        contenido.add(new Circulo(4,200,700));
        contenido.add(new Circulo(5,550,700));
        contenido.add(new Circulo(6,850,700));
        contenido.add(new Circulo(7,200,1000));
        contenido.add(new Circulo(8,550,1000));
        contenido.add(new Circulo(9,850,1000));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        for (Circulo cir:
             contenido) {
            cir.draw(canvas,paint);
        }
        int i = 0;
        for(Path trazo: paths){
            canvas.drawPath(trazo,paints.get(i++));
        }
        if(bandera2){
            loggin();
        }
        Log.d("CONTEO",""+resultado);
    }
    public void loggin(){
        if(bandera){

            Log.d("PRUEBA",""+resultado);
            if(password.equals(resultado)){
                Toast.makeText(nuevoC,"CONTRASEÑA CORRECTA",Toast.LENGTH_SHORT).show();
                bandera = false;
            }else{
                Toast.makeText(nuevoC,"INTENTA DENUEVO",Toast.LENGTH_SHORT).show();
                resultado = "";
                paint2.clearShadowLayer();
                paints.clear();
                path.close();
                paths.clear();
                for (Circulo cir: contenido) {
                    cir.bandera = true;
                }
                bandera2 = false;
                bandera = true;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        posx = (int) event.getX();
        posy = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                paint2 = new Paint();
                paint2.setStrokeWidth(20);
                paint2.setARGB(255,255,0,0);
                paint2.setStyle(Paint.Style.STROKE);
                paints.add(paint2);
                path = new Path();
                path.moveTo(posx,posy);
                paths.add(path);
//                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                int puntosHistoricos = event.getHistorySize();
                for (int i = 0; i< puntosHistoricos;i++){
                    path.lineTo(event.getHistoricalX(i),event.getHistoricalY(i));
                }
                for (Circulo cir: contenido) {
                    if(cir.inside(posx,posy) && cir.getBandera()){
                        cir.bandera = false;
                        resultado+= cir.numero();
                    }
                }
                break;
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            bandera2 = true;
        }
        invalidate();
        return true;
    }
}
