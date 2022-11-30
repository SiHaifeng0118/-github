package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText a = findViewById(R.id.zhuce_shouji);
        EditText b = findViewById(R.id.zhuce_mima);
        Button c = findViewById(R.id.wancheng_anniu);
        Button d = findViewById(R.id.fanhui_anniu);

        String a1 = a.getText().toString();
        String b1 = b.getText().toString();

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pattern pattern = Pattern.compile("^-?[0-9]+");
                if(1==1){
                    Intent intent = new Intent(RegisterActivity.this, PersonActivity.class);
                    intent.putExtra("fanhuimima",b1);
                    intent.putExtra("fanhuishouji",a1);
                    setResult(RESULT_OK,intent);
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT);
                    finish();
                }else {
                    b.setText(null);
                    Toast toast= Toast.makeText(RegisterActivity.this, "请输入一串数字", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP,0,0);
                    toast.show();
                }


            }
        });


        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}