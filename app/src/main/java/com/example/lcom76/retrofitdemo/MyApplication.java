package com.example.lcom76.retrofitdemo;

import android.app.Application;

import com.example.lcom76.retrofitdemo.GsonModel.Album;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by lcom76 on 23/6/16.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .setModules(new Album())
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }
}
