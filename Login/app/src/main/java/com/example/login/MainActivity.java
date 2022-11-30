package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    String aa;
    String bb;
    String path;
    FileInputStream inputStream;
    BufferedReader bufferedReader;
    String reader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText et1 = findViewById(R.id.denglu_shouji);
        EditText et2 = findViewById(R.id.denglu_mima);
        Button bt1 = findViewById(R.id.denglu_anniu);
        Button bt2 = findViewById(R.id.zhuce_anniu);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if((et2.getText().toString().length()>=6)&&(et2.getText().toString().length()<=12)&&(et1.getText().toString().length()==11)&&(et1.getText().toString().matches("\\d+"))&&(et2.getText().toString().matches("[A-Za-z0-9_]{6,}"))){
                   Intent intent = new Intent(MainActivity.this, PersonActivity.class);
                   startActivity(intent);
               }else if (et1.getText().toString().equals("")||et2.getText().toString().equals("")){
                   Toast.makeText(MainActivity.this,"手机号或密码不能为空",Toast.LENGTH_SHORT).show();
               }else if (et1.getText().toString().length()!=11){
                   Toast.makeText(MainActivity.this,"请输入11位手机号",Toast.LENGTH_SHORT).show();
               }else if (et2.getText().toString().length()<6){
                   Toast.makeText(MainActivity.this,"密码长度不得低于6位",Toast.LENGTH_SHORT).show();
               }else if (et2.getText().toString().length()>12){
                   Toast.makeText(MainActivity.this,"密码长度不得高于12位",Toast.LENGTH_SHORT).show();
               }else if (et2.getText().toString().matches("^[A-Za-z0-9]{6,}")){
                   Toast.makeText(MainActivity.this,"密码仅为大小写字母或数字",Toast.LENGTH_SHORT).show();
               }


            }
        });

        CheckBox checkBox =  findViewById(R.id.check1);
        EditText editText = findViewById(R.id.denglu_mima);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    editText.setInputType(InputType.TYPE_CLASS_TEXT);
                }else {
                    editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });

    }





}