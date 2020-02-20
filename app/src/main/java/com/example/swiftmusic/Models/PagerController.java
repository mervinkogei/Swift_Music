package com.example.swiftmusic.Models;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.swiftmusic.Fragments.AllMusic;
import com.example.swiftmusic.Fragments.CurrentMusic;
import com.example.swiftmusic.Fragments.Playlists;

public class PagerController extends FragmentPagerAdapter {
    int tabCounts;

    public PagerController(FragmentManager fm, int tabCounts) {
        super(fm);
        this.tabCounts = tabCounts;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return  new CurrentMusic();
            case 1:
                return new AllMusic();
            case 2:
                return new Playlists();

                default: return null;
        }
//        return null;
    }

    @Override
    public int getCount() {
        return tabCounts;
    }
}
