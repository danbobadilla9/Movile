package com.example.examen.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examen.R;

public class MenuFragment extends Fragment {
    private static EditText M;
    private static EditText N;
    private Button enviar;
    private DataListener callback;

    public MenuFragment() {
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (DataListener) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString());
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        enviar = (Button) view.findViewById(R.id.button);
        M = (EditText) view.findViewById(R.id.etM);
        N = (EditText) view.findViewById(R.id.etN);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(M.getText().toString() )== Integer.parseInt(N.getText().toString())){
                    sendNumber(Integer.parseInt(M.getText().toString()),Integer.parseInt(N.getText().toString()));
                }else{
                    Toast.makeText(getContext(),"Ingresa una matriz Cuadrada", Toast.LENGTH_LONG).show();
                    M.setText("");
                    N.setText("");
                }
            }
        });
        return view;
    }
    public static void clearMn(){
        M.setText("");
        N.setText("");
    }
    public void sendNumber(int M, int N){
        callback.sendData(M,N);
    }
    public interface DataListener{
        void sendData(int M, int N);
    }
}