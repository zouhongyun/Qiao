package com.example.two.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.two.HomeActivity;
import com.example.two.R;
import com.example.two.bean.Good;
import com.example.two.callback.Onclicklenter;

import java.util.ArrayList;

public class Home_Adapter extends RecyclerView.Adapter {
    private final Context context;
    private final ArrayList<Good> list;

    public void setOnclicklenter(Onclicklenter onclicklenter) {
        this.onclicklenter = onclicklenter;
    }

    private Onclicklenter onclicklenter;

    public Home_Adapter(Context context, ArrayList<Good> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_home, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder viewHolder1= (ViewHolder) viewHolder;
        viewHolder1.img.setImageResource(R.mipmap.ic_launcher);
        viewHolder1.content.setText(list.get(i).getContent());
        viewHolder1.title.setText(list.get(i).getTitle());
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

        private final TextView title;
        private final TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.home_img);
            content = itemView.findViewById(R.id.home_content);
            title = itemView.findViewById(R.id.home_title);
        }
    }
}
