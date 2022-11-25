package com.example.phototest;


import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Parcelable;
import android.util.Log;
import android.view.FocusFinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.regex.Pattern;

import bean.Picture;


public class RecVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Picture> list = new ArrayList<>();
    public static final int DATE = 0;
    public static final int IMAGE = 1;

    public RecVAdapter(List<Picture> list) {
        this.list = list;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        ImageView imageView;
        TextView textView;
             public MyHolder(@NonNull View itemView) {
              super(itemView);
              imageView = itemView.findViewById(R.id.iv);
              linearLayout = itemView.findViewById(R.id.item1);
              textView = itemView.findViewById(R.id.time);
              }
    }
    class DateHolder extends RecyclerView.ViewHolder{
            TextView textView;
        public DateHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvv);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == IMAGE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
            MyHolder holder = new MyHolder(view);
            return holder;
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dateitem,parent,false);
             DateHolder holder = new DateHolder(view);
            return holder;
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyHolder){
            Glide.with(MyApplication.getContext()).load(list.get(position).getPath()).into(((MyHolder)holder).imageView);
            String path = list.get(holder.getAdapterPosition()).getPath();
            String expath = path.substring(path.indexOf(".")+1);
            if(expath.equals("mp4")){
                String a = timestyle(getLocalVideoDuration(path));
                ((MyHolder)holder).textView.setText(a);
            }
            ((MyHolder)holder).linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    if(expath.equals("mp4"))
                    {
                        intent = new Intent(MyApplication.getContext(),MovieActivity.class);
                    }else {
                        intent = new Intent(MyApplication.getContext(),PictureActivity.class);
                    }
                    String path = list.get(holder.getAdapterPosition()).getPath();
                    intent.putExtra("picturepath", path);
                    intent.putExtra("pictureDate",list.get(holder.getAdapterPosition()).getDate());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyApplication.getContext().startActivity(intent);

                }
            });
        }else if (holder instanceof DateHolder){

           ((DateHolder)holder).textView.setText(list.get(holder.getAdapterPosition()).getDate());
            Log.d(TAG, "onBindViewHolder: qwer"+list.get(position).getDate());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return DATE;
        }else if (!(list.get(position-1).getDate().equals(list.get(position).getDate()))){
            return DATE;
        }else {
            return IMAGE;
        }

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == DATE
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * get Local video duration
     *
     * @return
     */
    public static int getLocalVideoDuration(String videoPath) {
                 //除以 1000 返回是秒
        int duration;
        try {
            MediaMetadataRetriever mmr = new  MediaMetadataRetriever();
            mmr.setDataSource(videoPath);
            duration = Integer.parseInt(mmr.extractMetadata
                    (MediaMetadataRetriever.METADATA_KEY_DURATION))/1000;
               //时长(毫秒)
               //String duration = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION);
               //宽
            String width = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
               //高
            String height = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return duration;
    }

    public String timestyle(int math){
        int min = math / 60;
        int second = math % 60;
        String a;
        if(second < 10){
             a = "0"+min+":0"+second;
        }else {
             a ="0"+ min + ":"+second;
        }
        return a;
    }

}
