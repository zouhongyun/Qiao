package com.example.day03;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Main2Activity;
import com.example.day03.adapter.MyAdapter;
import com.example.day03.bean.ListBean;
import com.example.day03.callbcack.Onclicklenter;
import com.example.day03.model.ListModelimple;
import com.example.day03.persenter.ListPerimple;
import com.example.day03.view.Listview;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

//邹鸿运 1810B
public class MainActivity extends AppCompatActivity implements Listview {

    /**
     * 户外花朵识别系统
     */
    private TextView mTv;
    private Toolbar mTl;
    private RecyclerView mRv;
    private NavigationView mNv;
    private DrawerLayout mDl;
    private ArrayList<ListBean.ImageListBean> list;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        initData();
        initView();
        initToolbar();
    }

    private void initData() {
        ListPerimple listPerimple = new ListPerimple(new ListModelimple(), this);
        listPerimple.getdata();

    }

    private void initToolbar() {
        mTl.setTitle("");
        setSupportActionBar(mTl);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDl, mTl, R.string.app_nadmes, R.string.app_nadmes);
        mDl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
        mTl = (Toolbar) findViewById(R.id.tl);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mNv = (NavigationView) findViewById(R.id.nv);
        mDl = (DrawerLayout) findViewById(R.id.dl);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRv.setLayoutManager(gridLayoutManager);
        adapter = new MyAdapter(this,list);
        mRv.setAdapter(adapter);

        adapter.setOnclicklenter(new Onclicklenter() {
            @Override
            public void Onclick(int position) {

               startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });

    }

    @Override
    public void ListSeccess(ListBean listBean) {
        list.addAll(listBean.getImage_list());
        adapter.notifyDataSetChanged();
        EventBus.getDefault().postSticky(listBean);
    }

    @Override
    public void ListFail(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
