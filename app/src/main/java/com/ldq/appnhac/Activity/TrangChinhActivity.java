package com.ldq.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.ldq.appnhac.Adapter.MainViewPagerAdapter;
import com.ldq.appnhac.Fragment.Fragment_Ho_So;
import com.ldq.appnhac.Fragment.Fragment_Tim_Kiem;
import com.ldq.appnhac.Fragment.Fragment_Trang_Chu;
import com.ldq.appnhac.R;

public class TrangChinhActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chinh);

        anhxa();
        init();
    }
    public void init(){
        MainViewPagerAdapter mainViewPagerAdapter= new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(),"trang chu");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"tim kiem");
        mainViewPagerAdapter.addFragment(new Fragment_Ho_So(),"ho so");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconhoso);
    }

    public void anhxa(){
        tabLayout=  (TabLayout) findViewById(R.id.myTabLayout);
        viewPager=  (ViewPager) findViewById(R.id.myViewPager);
    }
}
