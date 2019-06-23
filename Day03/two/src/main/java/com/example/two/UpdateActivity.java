package com.example.two;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.two.bean.Good;
import com.example.two.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    public static int TYPE_ONE = 1; //修改
    public static int TYPE_TWO = 2; //增加
    private EditText mUpdateImg;
    /**
     * two
     */
    private EditText mUpdateContent;
    /**
     * two
     */
    private EditText mUpdateTitle;
    /**
     * 修改
     */
    private Button mBtnUpdate;
    private Long id;
    private TextView mTvToolbarUpdate;
    private Toolbar mToolbarUpdate;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        type = intent.getIntExtra("Type", 1);
        initView();
        mToolbarUpdate.setTitle("");
        setSupportActionBar(mToolbarUpdate);
        EventBus.getDefault().register(this);
    }

    private void initView() {
        mUpdateImg = (EditText) findViewById(R.id.update_img);
        mUpdateContent = (EditText) findViewById(R.id.update_content);
        mUpdateTitle = (EditText) findViewById(R.id.update_title);
        mBtnUpdate = (Button) findViewById(R.id.btn_update);
        mBtnUpdate.setOnClickListener(this);
        mTvToolbarUpdate = (TextView) findViewById(R.id.tv_toolbar_update);
        mToolbarUpdate = (Toolbar) findViewById(R.id.toolbar_update);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_update:
                if (type==TYPE_ONE) {
                    String content1 = mUpdateContent.getText().toString();
                    String title1 = mUpdateTitle.getText().toString();
                    Good good = new Good(id, R.mipmap.ic_launcher, content1, title1);
                    Utils.utt().update(good);
                    finish();
                }else {
                    //添加
                    //先获取输入框的数据
                    //然后添加进数据库
                    String content1 = mUpdateContent.getText().toString();
                    String title1 = mUpdateTitle.getText().toString();
                    Good good = new Good(null, R.mipmap.ic_launcher, content1, title1);
                    Utils.utt().insert(good);
                    finish();
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void show(Good good) {

        if (type == 1) {
            mUpdateImg.setText(good.getImg());
            mUpdateContent.setText(good.getContent());
            mUpdateTitle.setText(good.getTitle());
            id = good.getId();
            mTvToolbarUpdate.setText("修改");
        } else {
            mTvToolbarUpdate.setText("增加");
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
