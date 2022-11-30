package fragment;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phototest.BaiduMapActivity;
import com.example.phototest.MainActivity;
import com.example.phototest.MyApplication;
import com.example.phototest.R;
import com.example.phototest.RecVAdapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import bean.Picture;

public class PictureFragment extends Fragment {
    private List<Picture> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.pic_fragment,container,false);
        myCursor();
        myrecyclerview(view);


        return view;
    }

    private void myCursor(){
        Cursor cursor = MyApplication.getContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,null,null,null);
        int indexPhotoPath = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        Cursor cursor1 = MyApplication.getContext().getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null,null,null,null);
        int VideoPath = cursor1.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
        Log.d(TAG, "myCursor: aaaa||||"+cursor.getCount());
        while (cursor.moveToNext()) {
            File file = new File(cursor.getString(indexPhotoPath));
            Long a = file.lastModified();
            Date dat = new Date(a);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String as = format.format(dat);

            String path = cursor.getString(indexPhotoPath);
            Picture picture = new Picture(path, as);
            list.add(picture);
        }
        while (cursor1.moveToNext()){
            File file = new File(cursor1.getString(VideoPath));
            Long a = file.lastModified();
            Date dat = new Date(a);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String as = format.format(dat);
            String Videopath = cursor1.getString(VideoPath);
            Picture picture = new Picture(Videopath,as);
            list.add(picture);
        }

        Collections.sort(list);
        cursor.close();
    }

    private void myrecyclerview(View view){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MyApplication.getContext(),4);
        RecyclerView recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(gridLayoutManager);
        RecVAdapter recVAdapter = new RecVAdapter(list);
        recyclerView.setAdapter(recVAdapter);
    }

}
