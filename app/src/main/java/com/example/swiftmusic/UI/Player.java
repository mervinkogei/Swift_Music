package com.example.swiftmusic.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
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


        //Checking if MediaPlayer is null
        if (mMediaPlayer !=null){
            mMediaPlayer.stop();
        }

        Intent songData = getIntent();
        songExtraData = songData.getExtras();

        songFileList = (ArrayList) songExtraData.getParcelableArrayList("songFileList");
        int position = songExtraData.getInt("position",0);
        initMusicPlay(position);// Start the media player

        //Set up the play/pause button

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });

    }

    private  void initMusicPlay(int position) {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.reset();

        }
        String name = songFileList.get(position).getName();
        mSongTitle.setText(name);

        //Get music patch from the sdCard
        Uri songResourceUri = Uri.parse(songFileList.get(position).toString());

        //Create a Media Player
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), songResourceUri);

        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //Set seek bar Duration
                mSeekBar.setMax(mMediaPlayer.getDuration());

                //Start the Music Player
                mMediaPlayer.start();

                //Set Icon to pause
                play.setImageResource(R.drawable.pause_btn);


            }
        });

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //do something when the song finished

                play.setImageResource(R.drawable.ic_play_arrow_black_24dp);

            }
        });

        //setting the seekbar
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser) {
                    mMediaPlayer.seekTo(progress); //seek the song
                    mSeekBar.setProgress(progress); //seek the progress
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //setup seekbar to change the duration
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mMediaPlayer != null) {
                    try {
                        if (mMediaPlayer.isPlaying()) {
                            Message message = new Message();
                            message.what = mMediaPlayer.getCurrentPosition();
                            handler.sendMessage(message);
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

        //Create Handler to set the progress

        @SuppressLint("HandlerLeak")
        private Handler handler = new Handler(){
            @Override
            public  void handleMessage (Message msg){
                mSeekBar.setProgress(msg.what);
            }
        };



        //Now we can play music.
//        mMediaPlayer.start();
//
//        if (mMediaPlayer.isPlaying()){
//            play.setImageResource(R.drawable.pause_btn);
//        }else{
//            play.setImageResource(R.drawable.ic_play_arrow_black_24dp);
//        }

        private void play(){
            if (mMediaPlayer !=null &&  mMediaPlayer.isPlaying()){
                mMediaPlayer.pause();
                play.setImageResource(R.drawable.ic_play_arrow_black_24dp);

            }else {
                mMediaPlayer.start();
                play.setImageResource(R.drawable.pause_btn);

            }
        }
}
