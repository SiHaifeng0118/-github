package com.example.phototest;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MovieActivity extends AppCompatActivity {
    public static final int asd = 1;

    private Handler handler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView  shijian= findViewById(R.id.shijian );
        setContentView(R.layout.activity_movie);
        Intent intent = getIntent();
        String path = intent.getStringExtra("picturepath");



        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case asd:
                        String a = (String) msg.obj;
                        try {
                            TextView  shijian= findViewById(R.id.shijian );
                            shijian.setText(a);
                        }catch (RuntimeException e){
                            TextView  shijian= findViewById(R.id.shijian );
                            shijian.setText(a);

                        }
                        break;
                    default:
                        break;

                }
            }
        };




        VideoView view1 = findViewById(R.id.vv);

        view1.setVideoPath(path);
        view1.start();

        int now =  view1.getDuration();
        int all = view1.getCurrentPosition();
        view1.requestFocus();

        ImageView bofang_zantin = findViewById(R.id.movie_bofang);

        SeekBar seekBar = findViewById(R.id.seekbar);


        bofang_zantin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(true == view1.isPlaying()){
                    bofang_zantin.setImageResource(R.drawable.bofang1);
                    view1.pause();


                }else {
                    bofang_zantin.setImageResource(R.drawable.zantin);
                    view1.start();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    view1.seekTo(i);
                }else {
                    seekBar.setProgress(view1.getCurrentPosition());
                }


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(view1.getCurrentPosition());
                Message message = new Message();
                message.what=asd;
                message.obj =  sty(view1.getCurrentPosition())+"/"+sty(view1.getDuration());
                handler.sendMessage(message);


            }
        },5,50);




    }
    private String sty(int math){
        int a = math/1000;
        if(a>= 10){
            return "00:"+String.valueOf(a);
        }
        return "00:0"+String.valueOf(a) ;
    }




}