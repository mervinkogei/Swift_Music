package com.example.swiftmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TabLayout mTabLayout;
    TabItem currentMusic;
    TabItem allMusic;
    TabItem playlist;
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Swift Music");

        mTabLayout = findViewById(R.id.tabLayout);
        currentMusic = findViewById(R.id.currentMusic);
        allMusic= findViewById(R.id.allMusic);
        playlist = findViewById(R.id.playlist);
        mPager = findViewById(R.id.viewpager);
    }
}
