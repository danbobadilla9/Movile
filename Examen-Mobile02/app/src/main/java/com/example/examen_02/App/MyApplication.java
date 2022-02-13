package com.example.examen_02.App;

import android.app.Application;

import com.example.examen_02.models.Datos;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApplication extends Application {
    public static AtomicInteger DatosId = new AtomicInteger();
    @Override
    public void onCreate() {
        super.onCreate();
        setUpRealmConfig();
        Realm realm = Realm.getDefaultInstance();
        DatosId = getIdByTable(realm, Datos.class);
        realm.close();
    }

    private void setUpRealmConfig(){
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

     private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){
         RealmResults<T> results = realm.where(anyClass).findAll();
        return  (results.size()>0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
     }
}
