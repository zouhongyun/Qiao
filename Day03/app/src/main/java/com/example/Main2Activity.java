package com.example;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.day03.R;
import com.example.day03.bean.ListBean;
import com.example.day03.persenter.ListPerimple;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private Toolbar mTvTl;
    private Banner mBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        mTvTl = (Toolbar) findViewById(R.id.tv_tl);
        mBanner = (Banner) findViewById(R.id.banner);
    }
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)

    public void show(ListBean listBean){
        List<ListBean.ImageListBean> imageList = listBean.getImage_list();
        Log.i("Tag", "show: "+imageList.toString());
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < imageList.size(); i++) {
            strings.add(imageList.get(i).getImage_url());
        }
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).setImages(strings).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
