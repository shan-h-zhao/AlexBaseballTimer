package com.example.alexbaseballtimer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    public String convertTime (Date milliSeconds) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); // HH for 0-23
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return df.format(milliSeconds);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        SeekBar seekBar = findViewById(R.id.seekBar);

        seekBar.setMax(30);
        seekBar.setMin(3);

        Date d = new Date(seekBar.getProgress()* 1000L);
        textView.setText(convertTime (d));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("value", Integer.toString(seekBar.getProgress()));
                Date d = new Date(seekBar.getProgress()* 1000L);
                textView.setText(convertTime (d));

                Button button= findViewById(R.id.button);
                button.setOnClickListener(view -> new CountDownTimer(seekBar.getProgress()* 1000L,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Date d1 = new Date(millisUntilFinished);
                        textView.setText(convertTime (d1));
                    }

                    @Override
                    public void onFinish() {
                        // Play sound
                        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.baseball);
                        mediaPlayer.start();

                        // Reset clock display
                        Date d1 = new Date(seekBar.getProgress()* 1000L);
                        textView.setText(convertTime (d1));
                    }
                }.start());
            }



//                new CountDownTimer(seekBar.getProgress()*1000,1000) {
//
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        Date d = new Date(millisUntilFinished);
//                        textView.setText(convertTime (d));
//                    }
//
//                    @Override
//                    public void onFinish() {
//
//                        Date d = new Date(seekBar.getProgress()* 1000L);
//                        textView.setText(convertTime (d));
//
//
//
//                    }
//                }.start();



            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }
}