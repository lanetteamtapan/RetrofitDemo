package com.example.lcom76.retrofitdemo.GsonModel;

import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmModule;

/**
 * Created by lcom76 on 21/6/16.
 */

@RealmModule(classes = {Album.class, Url.class})
public class Album extends RealmObject {

    @Nullable
    private String timestamp;

    @PrimaryKey
    private String name;

    @Nullable
    private Url url;


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }


//    {}
//    @Override
//    public String toString() {
//        return "ClassPojo [timestamp = " + timestamp + ", name = " + name + ", url = " + url + "]";
//    }
}
