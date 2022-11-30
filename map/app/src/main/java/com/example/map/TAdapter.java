package com.example.map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TAdapter extends RecyclerView.Adapter<TAdapter.ViewHolder> {
    private List<T4sDian> list;

    public TAdapter(List<T4sDian> list) {
        this.list = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView imageView1;
        TextView textView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.ivxuhao);
            this.imageView1 = itemView.findViewById(R.id.ivtupian);
            this.textView = itemView.findViewById(R.id.tvdianmin);
            this.textView1 = itemView.findViewById(R.id.tvdianhua);
            this. textView2 = itemView.findViewById(R.id.tvjuli);
            this. textView3 = itemView.findViewById(R.id.tvdizhi);
            this.button = itemView.findViewById(R.id.bt);
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
        holder.imageView.setImageResource(list.get( position).getXuHao());
        holder. imageView1.setImageResource(list.get( position).getTuPian());
        holder. textView.setText(list.get( position).getDianMin());
        holder.textView1.setText(list.get( position).getDianHua());
        holder. textView2.setText(list.get( position).getJuLi());
        holder. textView3.setText(list.get( position).getDiDian());
        holder.button.setText("删除");




    }




    @Override
    public int getItemCount() {
        return list.size();
    }
}
