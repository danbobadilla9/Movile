package com.example.examen_02.models;

import com.example.examen_02.App.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Datos extends RealmObject {
    @PrimaryKey
    private int id;
    @Required
    private String nombre;
    @Required
    private String valor;

    public Datos(){

    }

    public Datos(String nombre, String valor) {
        this.id = MyApplication.DatosId.incrementAndGet();
        this.nombre = nombre;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
