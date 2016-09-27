package com.example.lcom76.retrofitdemo.RealmController;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.lcom76.retrofitdemo.GsonModel.Album;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by lcom76 on 23/6/16.
 */

public class RealmController {

    private static RealmController instance;
    private final Realm realm;


    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }


    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }


    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    public void refresh() {

        realm.refresh();
    }


    public void clearAll() {

        realm.beginTransaction();
        realm.clear(Album.class);
        realm.commitTransaction();
    }

    public List<Album> getAllAlbums() {


        RealmResults<Album> results = realm.where(Album.class).findAll();

        return results.subList(0, results.size());
    }

}

