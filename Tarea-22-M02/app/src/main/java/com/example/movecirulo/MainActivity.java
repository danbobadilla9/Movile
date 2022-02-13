package com.example.movecirulo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.view.*;
public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    private int x, y;
    private Lienzo l;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        x = 100;
        y = 100;
        ConstraintLayout cl = findViewById(R.id.xl1);
        l = new Lienzo(this);
        l.setOnTouchListener(this);
        cl.addView(l);
    }
    public boolean onTouch(View v, MotionEvent e) {
        x = (int) e.getX();
        y = (int) e.getY();
        l.invalidate();
        return true;
    }
    class Lienzo extends View {
        public Lienzo(Context c) {
            super(c);
        }
        protected void onDraw(Canvas c) {
            c.drawRGB(255, 255, 0);
            Paint p = new Paint();
            p.setARGB(255, 255, 0, 0);
            p.setStrokeWidth(4);
            p.setStyle(Paint.Style.STROKE);
            c.drawCircle(x, y, 20, p);
        }
    }
}
