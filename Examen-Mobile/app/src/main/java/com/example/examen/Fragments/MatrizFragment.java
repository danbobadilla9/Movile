package com.example.examen.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.examen.Inversa;
import com.example.examen.R;

import java.util.ArrayList;
import java.util.List;

public class MatrizFragment extends Fragment {
    private View view;
    private Button cacular;
    private Button clear;
    private int tam;
    private float[][] matriz;
    private List<EditText> allEds = new ArrayList<EditText>();
    private TextView textView;
    private LinearLayout layout;
    private ClearMenu callback;
    public MatrizFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (ClearMenu) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_matriz, container, false);
        cacular = (Button) view.findViewById(R.id.calcular);
        cacular.setVisibility(View.GONE);
        textView = (TextView) view.findViewById(R.id.ejemplo2);
        textView.setVisibility(View.GONE);
        clear = (Button) view.findViewById(R.id.clear);
        clear.setVisibility(View.GONE);
        cacular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear.setVisibility(View.VISIBLE);
                matriz = new float[tam][tam];
//                for(int i=0;i<allEds.size();i++){
//                    matriz[i] = Float.parseFloat(allEds.get(i).getText().toString());
//                }
                textView.setText("A^-1");
                setMatriz();
                resultado();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.removeAllViewsInLayout();
                textView.setText("");
                clear.setVisibility(View.GONE);
                cacular.setVisibility(View.GONE);
                allEds.clear();
                callback.ClearData();
            }
        });
        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void mostrarDatos(int M, int N){
        cacular.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        this.tam = M;
        layout = (LinearLayout) view.findViewById(R.id.contenedorP);
        GridLayout contenedor = new GridLayout(view.getContext());
        contenedor.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        contenedor.setColumnCount(M);
        contenedor.setRowCount(M);
            for(int i =0; i<M*M;i++){
                GridLayout.LayoutParams param= new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED,GridLayout.FILL,1f), GridLayout.spec(GridLayout.UNDEFINED,GridLayout.FILL,1f));
                EditText editText = new EditText(view.getContext());
                allEds.add(editText);
                editText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText.setHint(""+i);
                editText.setId(i);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setLayoutParams(param);
                contenedor.addView(editText);
            }
        layout.addView(contenedor);
    }
    public void setMatriz(){
        int contador = 0,j=0;
        for(int i=0; i<allEds.size();i++){
            matriz[contador][j] = Float.parseFloat(allEds.get(i).getText().toString());
            j++;
            if(j==tam){
                j=0;
            }
            if(((i+1) % tam == 0) && contador < (tam-1)){
                contador++;
            }
        }
        matriz = Inversa.invert(matriz);
    }
    public void resultado(){
        int contador = 0;
        for(int i = 0; i<tam;i++){
            for(int j=0;j<tam;j++){
                float numero = (float) (Math.round(matriz[i][j]*100.0)/100.0);
                allEds.get(contador).setText(String.valueOf(numero));
                contador++;
            }
        }
    }
    public interface ClearMenu{
        void ClearData();
    }
}