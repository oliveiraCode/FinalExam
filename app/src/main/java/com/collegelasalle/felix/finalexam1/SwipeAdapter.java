package com.collegelasalle.felix.finalexam1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SwipeAdapter extends FragmentStatePagerAdapter {

    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            default:
            case 0:
                return new SensorFragment();
            case 1:
                return new LocationFragment();
            case 2:
                return new StorageFragment();
            case 3:
                return new GameFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
