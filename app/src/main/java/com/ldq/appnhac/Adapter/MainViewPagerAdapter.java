package com.ldq.appnhac.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> arrayPragment=  new ArrayList<>();
    private ArrayList<String>   arrayTittle=    new ArrayList<>();


    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return arrayPragment.get(position);
    }

    @Override
    public int getCount() {
        return arrayPragment.size();
    }

    public void addFragment(Fragment fragment, String tittle){
        arrayPragment.add(fragment);
        arrayTittle.add(tittle);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTittle.get(position);
    }
}
