package com.example.yuecheng.day10_activity1;
//callable,runnable
//use thread to mimic countdowntimer

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    public int[] imageId = {R.drawable.bomb, R.drawable.bombboom};
    MediaPlayer mplayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String audioPath = "android.resource://" + getPackageName() + "/" + R.raw.bomb;
        Uri uri = Uri.parse(audioPath);
        mplayer = new MediaPlayer().create(this, uri);


        final ImageView img = (ImageView) findViewById(R.id.imageView1);
        final TextView txv = (TextView) findViewById(R.id.textView);
        img.setImageResource(imageId[0]);



        //seekbar
        final SeekBar sb = (SeekBar) findViewById(R.id.seekBar);
        sb.setMax(100000);



        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBar.getProgress();
                new CountDownTimer(seekBar.getProgress(), 1000) {//for 10s, every 1s do something
                    @Override
                    public void onTick(long millisUntilFinished) {
                        //Log.i("Testing CountDownTimer", "Danger");
                        img.setImageResource(imageId[0]);
                        txv.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    @Override
                    public void onFinish() {
                        //Log.i("Testing CountDownTimer", "Boom!");
                        img.setImageResource(imageId[1]);
                        img.animate().scaleX(0.5f).scaleY(0.5f).rotation(360f).setDuration(5000);
                        txv.setText("EXPLODE!");

                        mplayer.start();
                    }
                }.start();

            }
        });






    }


}
