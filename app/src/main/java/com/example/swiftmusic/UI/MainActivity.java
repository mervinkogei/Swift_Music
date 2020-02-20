package com.example.swiftmusic.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.swiftmusic.Models.PagerController;
import com.example.swiftmusic.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TabLayout mTabLayout;
    TabItem currentMusic;
    TabItem allMusic;
    TabItem playlist;
    ViewPager mPager;
    PagerController mPagerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mToolbar = findViewById(R.id.toolbar);


////        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle("Swift Music");

        mTabLayout = findViewById(R.id.tabLayout);
        currentMusic = findViewById(R.id.currentMusic);
        allMusic= findViewById(R.id.allMusic);
        playlist = findViewById(R.id.playlist);
        mPager = findViewById(R.id.viewpager);

        mPagerController = new PagerController(getSupportFragmentManager(),mTabLayout.getTabCount());
        mPager.setAdapter(mPagerController);

        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
