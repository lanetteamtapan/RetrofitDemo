package com.example.lcom76.retrofitdemo.Adapers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lcom76.retrofitdemo.Fragments.NewTemplate1;
import com.example.lcom76.retrofitdemo.GsonModel.Album;

import java.util.List;

/**
 * Created by lcom76 on 17/6/16.
 */

public class NewsPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments;
    List<Album> data;
    public NewsPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<Album> data) {
        super(fm);
        this.fragments = fragments;
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getName();
    }
}
