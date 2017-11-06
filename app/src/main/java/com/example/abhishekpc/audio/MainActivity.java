package com.example.abhishekpc.audio;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
MediaPlayer mediaPlayer;
    AudioManager audioManager;
    public void play(View view){
        mediaPlayer.start();
    }
    public void pause(View view){
        mediaPlayer.pause();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.tvfsong);
        //getting context from audio service
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int mymaxvalue = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int mycurrentvolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //setting context value to seekbar
        SeekBar volumerocker = (SeekBar) findViewById(R.id.seekBar);
        volumerocker.setMax(mymaxvalue);
        volumerocker.setProgress(mycurrentvolume);

        //set on change listener

        volumerocker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //setting a player progess bar
        final SeekBar timeline = (SeekBar) findViewById(R.id.seekBar2);
        timeline.setMax(mediaPlayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeline.setProgress(mediaPlayer.getCurrentPosition());

            }
        }, 0, 1000);

        timeline.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
