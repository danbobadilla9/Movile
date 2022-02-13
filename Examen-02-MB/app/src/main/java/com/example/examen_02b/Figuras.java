package com.example.examen_02b;



import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Figuras {
    private int valor;
    private int x = 0,y = 0;
    public boolean bandera = true;
    private Rect borde;
    public String color;
    public Figuras(int valor, int x, int y, String color){
        this.valor = valor;
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public void draw(Canvas canvas, Paint paint){
        paint.setColor(Color.parseColor(color));
        canvas.drawCircle(x,y,75,paint);
        borde = new Rect(x-70,y-70,x+70,y+70);

    }
    public String getNumero(){
        return String.valueOf(valor);
    }
    public boolean getBandera(){
        return bandera;
    }
    public int getCenterX(){
        return borde.centerX();
    }
    public int getCenterY(){
        return borde.centerY();
    }
    public boolean inside(int x,int y){


        return borde.contains(x,y);
    }
}

