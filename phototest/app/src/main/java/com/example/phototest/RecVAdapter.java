package com.example.phototest;


import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
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
import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
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
        RelativeLayout linearLayout;
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
            try {
                String pa = list.get(position).getPath();
                ExifInterface exifInterface = new ExifInterface(pa);

                String TAG_GPS_LATITUDE = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
                String TAG_GPS_LONGITUDE = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
                String TAG_GPS_LATITUDE_REF = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
                String  TAG_GPS_LONGITUDE_REF = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);

                String guangquan = exifInterface.getAttribute(ExifInterface.TAG_APERTURE);
                String shijain = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
                String baoguangshijian = exifInterface.getAttribute(ExifInterface.TAG_EXPOSURE_TIME);
                String jiaoju = exifInterface.getAttribute(ExifInterface.TAG_FOCAL_LENGTH);
                String chang = exifInterface.getAttribute(ExifInterface.TAG_IMAGE_LENGTH);
                String kuan = exifInterface.getAttribute(ExifInterface.TAG_IMAGE_WIDTH);
                String moshi = exifInterface.getAttribute(ExifInterface.TAG_MODEL);
                String zhizaoshang = exifInterface.getAttribute(ExifInterface.TAG_MAKE);
                String iso = exifInterface.getAttribute(ExifInterface.TAG_ISO);
                String jiaodu = exifInterface.getAttribute(ExifInterface.TAG_ORIENTATION);
                String baiph = exifInterface.getAttribute(ExifInterface.TAG_WHITE_BALANCE);
                String altitude_ref = exifInterface.getAttribute(ExifInterface
                        .TAG_GPS_ALTITUDE_REF);
                String altitude = exifInterface.getAttribute(ExifInterface.TAG_GPS_ALTITUDE);
                String latitude = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
                String latitude_ref = exifInterface.getAttribute(ExifInterface
                        .TAG_GPS_LATITUDE_REF);
                String longitude_ref = exifInterface.getAttribute(ExifInterface
                        .TAG_GPS_LONGITUDE_REF);
                String longitude = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
                String timestamp = exifInterface.getAttribute(ExifInterface.TAG_GPS_TIMESTAMP);
                String processing_method = exifInterface.getAttribute(ExifInterface
                        .TAG_GPS_PROCESSING_METHOD);
                double lat = score2dimensionality(latitude);
                double lon = score2dimensionality(longitude);

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("光圈 = " + guangquan+"\n")
                        .append("时间 = " + shijain+"\n")
                        .append("曝光时长 = " + baoguangshijian+"\n")
                        .append("焦距 = " + jiaoju+"\n")
                        .append("长 = " + chang+"\n")
                        .append("宽 = " + kuan+"\n")
                        .append("型号 = " + moshi+"\n")
                        .append("制造商 = " + zhizaoshang+"\n")
                        .append("ISO = " + iso+"\n")
                        .append("角度 = " + jiaodu+"\n")
                        .append("白平衡 = " + baiph+"\n")
                        .append("海拔高度 = " + altitude_ref+"\n")
                        .append("GPS参考高度 = " + altitude+"\n")
                        .append("GPS时间戳 = " + timestamp+"\n")
                        .append("GPS定位类型 = " + processing_method+"\n")
                        .append("GPS参考经度 = " + latitude_ref+"\n")
                        .append("GPS参考纬度 = " + longitude_ref+"\n")
                        .append("GPS经度 = " + lat+"\n")
                        .append("GPS经度 = " + lon+"\n");
                Log.d(TAG, "onBindViewHolder: qwerqwer"+stringBuilder.toString());

                Geocoder gc = new Geocoder(MyApplication.getContext(),Locale.getDefault());
              //  float lat = (float) getRationalLatLonToFloat(TAG_GPS_LATITUDE, TAG_GPS_LATITUDE_REF);
                float longi = (float) getRationalLatLonToFloat(TAG_GPS_LONGITUDE, TAG_GPS_LONGITUDE_REF);

                Log.d(TAG, "onBindViewHolder: ccccc"+TAG_GPS_LATITUDE+TAG_GPS_LONGITUDE);
                List<Address> addresses = gc.getFromLocation(32.07f , 118.78f, 1);




            } catch (IOException e) {
                e.printStackTrace();
            }
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


    private static double getRationalLatLonToFloat(String rationalString, String ref) {
        if (rationalString == null || ref == null) return 0.0;
        String[] parts = rationalString.split(",");
        String[] pair;
        pair = parts[0].split("/");
        double degrees = Double.parseDouble(pair[0].trim()) / Double.parseDouble(pair[1].trim());
        pair = parts[1].split("/");
        double minutes = Double.parseDouble(pair[0].trim()) / Double.parseDouble(pair[1].trim());
        pair = parts[2].split("/");
        double seconds = Double.parseDouble(pair[0].trim()) / Double.parseDouble(pair[1].trim());
        double result = degrees + (minutes / 60.0) + (seconds / 3600.0);
        if ((ref.equals("S") || ref.equals("W"))) {
            return -result;
        }
        return result;
    }
    /**
     * 将 112/1,58/1,390971/10000 格式的经纬度转换成 112.99434397362694格式
     * @param string 度分秒
     * @return 度
     */
    private double score2dimensionality(String string) {
        double dimensionality = 0.0;
        if (null==string){
            return dimensionality;
        }

        //用 ，将数值分成3份
        String[] split = string.split(",");
        for (int i = 0; i < split.length; i++) {

            String[] s = split[i].split("/");
            //用112/1得到度分秒数值
            double v = Double.parseDouble(s[0]) / Double.parseDouble(s[1]);
            //将分秒分别除以60和3600得到度，并将度分秒相加
            dimensionality=dimensionality+v/Math.pow(60,i);
        }
        return dimensionality;
    }


}
