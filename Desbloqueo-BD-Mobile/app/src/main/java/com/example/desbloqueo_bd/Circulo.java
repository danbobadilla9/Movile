package com.example.desbloqueo_bd;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Circulo {
    private int numeroPasword;
    private int x = 0,y = 0;
    public boolean bandera = true;
    private Rect limitante;
    public Circulo (int numeroPasword,int x,int y){
        this.numeroPasword = numeroPasword;
        this.x = x;
        this.y = y;
    }
    public void draw(Canvas canvas, Paint paint){
        paint.setColor(Color.rgb(0,205,255));
        canvas.drawCircle(x,y,80,paint);
        limitante = new Rect(x-75,y-75,x+75,y+75);

    }
    public String numero(){
        return String.valueOf(numeroPasword);
    }
    public boolean getBandera(){
        return bandera;
    }
    public boolean inside(int x,int y){
        return limitante.contains(x,y);
    }
}

