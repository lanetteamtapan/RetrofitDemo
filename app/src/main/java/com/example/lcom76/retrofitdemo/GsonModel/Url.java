package com.example.lcom76.retrofitdemo.GsonModel;

import io.realm.RealmObject;

/**
 * Created by lcom76 on 21/6/16.
 */
public class Url extends RealmObject {
    private String small;

    private String large;

    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

//    @Override
//    public String toString() {
//        return "ClassPojo [small = " + small + ", large = " + large + ", medium = " + medium + "]";
//    }
}