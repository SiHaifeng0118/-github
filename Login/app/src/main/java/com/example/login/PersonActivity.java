package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class PersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        findViewById(R.id.touxianglayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonActivity.this, ReviseActivity.class);
                startActivity(intent);
            }
        });
       setpic();
    }

    private void setpic(){
        FileInputStream inputStream =null;
        BufferedReader bufferedReader = null;
        String imagePath = null;

        try {
            inputStream = openFileInput("data1");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            imagePath = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ImageView picture = findViewById(R.id.im_2);
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        picture.setImageBitmap(bitmap);
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0,0,picture.getWidth(), picture.getHeight(),50f);
            }
        };
        picture.setOutlineProvider(viewOutlineProvider);
        picture.setClipToOutline(true);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setpic();
    }
}