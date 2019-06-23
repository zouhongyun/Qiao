package com.example.two;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.two.adapter.Home_Adapter;
import com.example.two.bean.Good;
import com.example.two.callback.Onclicklenter;
import com.example.two.model.BeanModelimple;
import com.example.two.persenter.BeanPerimple;
import com.example.two.utils.Utils;
import com.example.two.view.BeanView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.InternalUnitTestDaoAccess;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements BeanView {

    /**
     * 首页
     */
    private TextView mTvToolbar;
    private Toolbar mTl;
    private RecyclerView mRvHome;
    private ArrayList<Good> list;
    private Home_Adapter adapter;
    private BeanPerimple beanPerimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        list = new ArrayList<>();
        initData();
        initView();
        initToolbar();
    }

    private void initToolbar() {
        mTl.setTitle("");
        setSupportActionBar(mTl);
    }

    private void initData() {
        beanPerimple = new BeanPerimple(new BeanModelimple(), this);
        beanPerimple.getdata();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,1,"增加");
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(HomeActivity.this,UpdateActivity.class);
                intent.putExtra("Type",UpdateActivity.TYPE_TWO);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //onRestart是生命周期的方法
        list.clear();
        beanPerimple.getdata();
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        mTvToolbar = (TextView) findViewById(R.id.tv_toolbar);
        mTl = (Toolbar) findViewById(R.id.tl);
        mRvHome = (RecyclerView) findViewById(R.id.rv_home);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvHome.setLayoutManager(linearLayoutManager);
        adapter = new Home_Adapter(this,list);
        mRvHome.setAdapter(adapter);
        adapter.setOnclicklenter(new Onclicklenter() {
            @Override
            public void Onclick(final int position) {
                final View inflate = LayoutInflater.from(HomeActivity.this).inflate(R.layout.popup, null, false);
                final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                popupWindow.showAtLocation(inflate, Gravity.CENTER,0,0);
                inflate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                inflate.findViewById(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Good good = list.get(position);
                        Utils.utt().delete(good);
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        popupWindow.dismiss();
                    }
                });
                inflate.findViewById(R.id.tv_update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Good good = list.get(position);
                        EventBus.getDefault().postSticky(good);
                        Intent intent = new Intent(HomeActivity.this,UpdateActivity.class);
                        intent.putExtra("Type",UpdateActivity.TYPE_ONE);
                        startActivity(intent);
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public void OnSeccess(List<Good> goods) {
        list.addAll(goods);

    }

    @Override
    public void OnFail(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
