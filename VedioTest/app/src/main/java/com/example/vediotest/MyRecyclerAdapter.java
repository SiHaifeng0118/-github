package com.example.vediotest;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        LinearLayout linearLayout;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             imageView = itemView.findViewById(R.id.iv);
             linearLayout = itemView.findViewById(R.id.ll);
         }

     }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(R.drawable.aa);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyApplication.getContext(),MaxPicture.class);
                intent.putExtra("path",R.drawable.aa);
                //为即将要启动的Activity提供一个任务栈，为待启动的Activity指定FLAG_ACTIVITY_NEW_TASK标记位。
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent);

            }
        });

    }



    @Override
    public int getItemCount() {
        return 100;
    }
}
