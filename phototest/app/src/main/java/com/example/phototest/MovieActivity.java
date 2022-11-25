package com.example.phototest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Intent intent = getIntent();
        String path = intent.getStringExtra("picturepath");
        VideoView view = findViewById(R.id.vv);
        view.setMediaController(new MediaController(this));
        view.setVideoPath(path);
        view.start();
        view.requestFocus();
    }
}