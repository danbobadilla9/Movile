package com.example.tarea28;

import android.content.*;
import android.graphics.*;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.View.*;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnDragListener,
        OnLongClickListener {
    TextView tv;
    ImageView iv,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9;
    Button bn;
    @Override
    protected void onCreate(Bundle bu) {
        super.onCreate(bu);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.lbl);
        tv.setTag("Texto");
        tv.setOnLongClickListener(this);
        //
        iv = (ImageView) findViewById(R.id.ingvw);
        iv.setTag("Imagen");
        iv.setOnLongClickListener(this);
        //
        iv2 = (ImageView) findViewById(R.id.ingvw2);
        iv2.setTag("Imagen2");
        iv2.setOnLongClickListener(this);
        //
        iv3 = (ImageView) findViewById(R.id.ingvw3);
        iv3.setTag("Imagen3");
        iv3.setOnLongClickListener(this);
        //
        iv4 = (ImageView) findViewById(R.id.ingvw4);
        iv4.setTag("Imagen4");
        iv4.setOnLongClickListener(this);
        //
        iv5 = (ImageView) findViewById(R.id.ingvw5);
        iv5.setTag("Imagen5");
        iv5.setOnLongClickListener(this);
        //
        iv6 = (ImageView) findViewById(R.id.ingvw6);
        iv6.setTag("Imagen6");
        iv6.setOnLongClickListener(this);
        //
        iv7 = (ImageView) findViewById(R.id.ingvw7);
        iv7.setTag("Imagen7");
        iv7.setOnLongClickListener(this);
        //
        iv8 = (ImageView) findViewById(R.id.ingvw8);
        iv8.setTag("Imagen8");
        iv8.setOnLongClickListener(this);
        //
        iv9 = (ImageView) findViewById(R.id.ingvw9);
        iv9.setTag("Imagen9");
        iv9.setOnLongClickListener(this);
        //
        bn = (Button) findViewById(R.id.btnDrag);
        bn.setTag("Botón");
        bn.setOnLongClickListener(this);
        findViewById(R.id.xll1).setOnDragListener(this);
        findViewById(R.id.xll2).setOnDragListener(this);
        findViewById(R.id.xll3).setOnDragListener(this);
    }
    @Override
    public boolean onLongClick(View v) {
        ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
        String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
        ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
        View.DragShadowBuilder dragshadow = new View.DragShadowBuilder(v);
        v.startDrag(data, dragshadow, v, 0); // elemento, su sombra, elemento local,
        return true;
    }
    @Override
    public boolean onDrag(View v, DragEvent e) {
        int a = e.getAction();
        switch (a) {
            case DragEvent.ACTION_DRAG_STARTED:
                if(e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
                    return true;
                }
                return false;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                v.invalidate();
                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                v.getBackground().clearColorFilter();
                v.invalidate();
                return true;
            case DragEvent.ACTION_DROP:
                ClipData.Item item = e.getClipData().getItemAt(0);
                String dragData = item.getText().toString();
                Toast.makeText(this, "Elemento movido " + dragData,
                        Toast.LENGTH_SHORT).show();
                v.invalidate();
                View vw = (View) e.getLocalState();
                ViewGroup owner = (ViewGroup) vw.getParent();
                owner.removeView(vw); //elimina elemento arrastrado
                LinearLayout container = (LinearLayout) v;
                container.addView(vw); //Add the dragged view
                vw.setVisibility(View.VISIBLE); //finalmente se hace visible
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                v.getBackground().clearColorFilter();
                v.invalidate();
                if (e.getResult())
                    Toast.makeText(this, "Elemento movido.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Elemento no movido",
                            Toast.LENGTH_SHORT).show();
                return true;
            default:
                Log.e("DragDrop", "Operación desconocida.");
                break;
        }
        return false;
    }
}