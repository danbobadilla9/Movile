package com.example.examen_02b;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Boton {
    public String texto = "Ingresa Un Nuevo Patron";
    private String color;
    private Rect borde;
    public Boton(String color){
        this.color = color;
    }
    public void draw(Canvas canvas, Paint paint){
        paint.setColor(Color.parseColor(color));
        canvas.drawRect(100,1200,1000,1400,paint);
        paint.setColor(Color.parseColor("BLACK"));
        paint.setTextSize(70);
        canvas.drawText(texto,100,200,paint);
        canvas.drawText("Nuevo Patron",300,1300,paint);
        borde = new Rect(100,1200,1000,1400);
    }

    public boolean inside(int x,int y){
        return borde.contains(x,y);
    }
}
