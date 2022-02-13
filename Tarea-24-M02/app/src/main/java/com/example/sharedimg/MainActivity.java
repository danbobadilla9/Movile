package com.example.sharedimg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button imagen;
    private Button cargada;
    private ImageView vista;
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    private int bandera;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagen = findViewById(R.id.Select);
        vista = findViewById(R.id.img);
        cargada = findViewById(R.id.Show);
        sp = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        bandera = sp.getInt("bandera",0);
        if(bandera == 0){
            cargada.setVisibility(View.GONE);
        }else{
            cargada.setVisibility(View.VISIBLE);
        }
        imagen.setOnClickListener(this);
        cargada.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.Select){
            if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_STORAGE_PERMISSION);
            }else{
                selectImg();
            }

        }
        if(view.getId() == R.id.Show){
            Uri select = Uri.parse(sp.getString("Uri",""));
            Log.d("PRUEBA",sp.getString("Uri",""));
            if(select != null){
                InputStream inputStream2 = null;
                try {
                    inputStream2 = getContentResolver().openInputStream(select);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream2);
                    vista.setImageBitmap(bitmap);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        }
    }
    public void selectImg(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent,REQUEST_CODE_SELECT_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                selectImg();
            }else{
                Toast.makeText(MainActivity.this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK){
            if(data != null){
                Uri selectedImageUri = data.getData();
                if(selectedImageUri != null){
                    try{
//                        InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
//                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                        vista.setImageBitmap(bitmap);
                        //Obteniendo las preferencias
                        SharedPreferences.Editor miEditor = sp.edit();
                        miEditor.putInt("bandera",1);
                        miEditor.putString("Uri",selectedImageUri.toString());
                        miEditor.commit();
                        Log.d("PRUEBA",selectedImageUri.toString());
                        cargada.setVisibility(View.VISIBLE);
                        Toast.makeText(this, "Imagen Guardada", Toast.LENGTH_SHORT).show();
                    }catch (Exception exception){
                        Toast.makeText(this, "No se pudo cargar la imagen", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}