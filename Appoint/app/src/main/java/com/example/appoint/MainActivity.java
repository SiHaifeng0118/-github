package com.example.appoint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    public EditText editText;
    public String string ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         editText = findViewById(R.id.et);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdialog();


            }
        });



    }

    protected void getdialog(){
        //点击弹出对话框
        final ExtendDialog editDialog = new ExtendDialog(MainActivity.this);
        editDialog.setTitle("备注");
        editDialog.setYesOnclickListener("保存", new ExtendDialog.onYesOnclickListener() {
            @Override
            public void onYesClick(String phone) {
                string = phone;
                editText.setText(string);
                editDialog.dismiss();

            }
        });

        editDialog.setNoOnclickListener("取消", new ExtendDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                editDialog.dismiss();
            }
        });
        editDialog.show();
    }



}