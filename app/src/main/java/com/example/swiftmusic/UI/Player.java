package com.example.swiftmusic.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.swiftmusic.R;

import java.io.File;
import java.util.ArrayList;

public class Player extends AppCompatActivity {
    Bundle songExtraData;
    ArrayList<File> songFileList;

    SeekBar mSeekBar;
    TextView mSongTitle;
    ImageView previous;
    ImageView play;
    ImageView next;
    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //Setting Resources
        mSeekBar = findViewById(R.id.seekBar);
        mSongTitle = findViewById(R.id.songTitle);
        previous = findViewById(R.id.previous);
        play = findViewById(R.id.play);
        next = findViewById(R.id.next);

        Intent songData = getIntent();
        songExtraData = songData.getExtras();

        songFileList = (ArrayList) songExtraData.getParcelableArrayList("songFileList");
        int position = songExtraData.getInt("position",0);
    }

    private  void initMusicPlay(int position){
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()){

        }

    }
}
