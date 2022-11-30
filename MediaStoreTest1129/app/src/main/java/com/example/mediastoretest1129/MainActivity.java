package com.example.mediastoretest1129;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.INTERNAL_CONTENT_URI,null
        ,null,null,null);
        int a = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

    }
}