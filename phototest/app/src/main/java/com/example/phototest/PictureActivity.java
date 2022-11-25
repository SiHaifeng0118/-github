package com.example.phototest;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.RecoverableSecurityException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import bean.Picture;
import view.PhotoView;

public class PictureActivity extends AppCompatActivity {
        String path;
        String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        PhotoView imageView = findViewById(R.id.PictureIV);
        imageView.enable();

        Intent intent = getIntent();
        path = intent.getStringExtra("picturepath");
        date = intent.getStringExtra("pictureDate");/////////////

        File file = new File(path);
        Glide.with(PictureActivity.this).load(path).into(imageView);

        Uri uri = Uri.fromFile(file);
        List<Uri> list = new ArrayList<>();
        list.add(uri);
    //    button.setOnClickListener(new View.OnClickListener() {
     //       @Override
    //        public void onClick(View view) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                    PendingIntent pi = MediaStore.createDeleteRequest(getContentResolver(), list);
//                    try {
//                        startIntentSenderForResult(
//                                pi.getIntentSender(), 1, null, 0, 0,
//                                0
//                        );
//                    } catch (IntentSender.SendIntentException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//               MediaStore.createDeleteRequest(getContentResolver(),list);
//            }
 //       });
    }

}