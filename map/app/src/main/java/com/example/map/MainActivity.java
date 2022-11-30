package com.example.map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected List<T4sDian> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.tv1);
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        init();

        TRecyclerView recyclerView = findViewById(R.id.lv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        TAdapter tAdapter = new TAdapter(list);
        recyclerView.setAdapter(tAdapter);
        recyclerView.setOnItemClickListenre(new TRecyclerView.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                list.remove(position);
                tAdapter.notifyItemRemoved(position);

            }
        });







    }

    protected void init(){
        list = new ArrayList<>();
        for (int i = 0 ;i <= 55 ; i++){
            list.add( new T4sDian(R.drawable.number,R.drawable.ic_launcher_background,"广汽传祺（汽车城店）"
                    ,"0551-8888666"+i,"16.6KM","｜ 安徽省合肥瑶海区汽车城路2号西北90米"));
        }



    }


}