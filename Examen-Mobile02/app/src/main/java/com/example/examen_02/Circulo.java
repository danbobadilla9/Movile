package com.example.examen_02;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Circulo {
    private int numeroPasword;
    private int x = 0,y = 0;
    public boolean bandera = true;
    private Rect limitante;
    public String color;
    public Circulo (int numeroPasword,int x,int y,String color){
        this.numeroPasword = numeroPasword;
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public void draw(Canvas canvas, Paint paint){
        paint.setColor(Color.parseColor(color));//Color.rgb(0,205,255)
        canvas.drawCircle(x,y,80,paint);
        limitante = new Rect(x-75,y-75,x+75,y+75);

    }
    public String numero(){
        return String.valueOf(numeroPasword);
    }
    public boolean getBandera(){
        return bandera;
    }
    public int getCenterX(){
        return limitante.centerX();
    }
    public int getCenterY(){
        return limitante.centerY();
    }
    public boolean inside(int x,int y){


        return limitante.contains(x,y);
    }
}
