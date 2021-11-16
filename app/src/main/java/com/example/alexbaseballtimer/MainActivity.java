package com.example.alexbaseballtimer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        SeekBar seekBar = findViewById(R.id.seekBar);

        seekBar.setMax(30);
        seekBar.setMin(3);

        textView.setText(Integer.toString(seekBar.getProgress()));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("value", Integer.toString(seekBar.getProgress()));
                Integer seekBarProgress = seekBar.getProgress();
                textView.setText(Integer.toString(seekBarProgress));

                new CountDownTimer(seekBarProgress*1000,1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                        Log.i("seconds remaining: ", String.valueOf(millisUntilFinished / 1000));

                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();

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