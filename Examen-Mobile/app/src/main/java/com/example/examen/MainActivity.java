package com.example.examen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Build;
import android.os.Bundle;

import com.example.examen.Fragments.MatrizFragment;
import com.example.examen.Fragments.MenuFragment;

public class MainActivity extends FragmentActivity implements MenuFragment.DataListener, MatrizFragment.ClearMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void sendData(int M, int N) {
        MatrizFragment matrizFragment = (MatrizFragment) getSupportFragmentManager().findFragmentById(R.id.MatrizFragment);
        matrizFragment.mostrarDatos(M,N);
    }

    @Override
    public void ClearData() {
        MenuFragment.clearMn();
    }
}