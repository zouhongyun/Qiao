package com.example.day03.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.day03.MainActivity;
import com.example.day03.R;
import com.example.day03.bean.ListBean;
import com.example.day03.callbcack.Onclicklenter;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final ArrayList<ListBean.ImageListBean> list;

    public void setOnclicklenter(Onclicklenter onclicklenter) {
        this.onclicklenter = onclicklenter;
    }

    private Onclicklenter onclicklenter;

    public MyAdapter(Context context, ArrayList<ListBean.ImageListBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_list, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
    ViewHolder viewHolder1= (ViewHolder) viewHolder;
        Glide.with(context).load(list.get(i).getImage_url()).into(viewHolder1.img);
        viewHolder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onclicklenter!=null){
                    onclicklenter.Onclick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }
}
