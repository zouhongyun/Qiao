package com.example.two;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

//邹鸿运 1810B
public class MainActivity extends AppCompatActivity {

    /**
     * 欢迎
     */
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);



                TranslateAnimation animation =new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f, Animation.RELATIVE_TO_SELF,1f,Animation.RELATIVE_TO_SELF,0f, Animation.RELATIVE_TO_SELF,1f);
                animation.setDuration(2000);
                animation.setInterpolator(this, android.R.anim.linear_interpolator);
                mTv.startAnimation(animation);
                animation.start();
                new CountDownTimer(3000,1000){
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        startActivity(new Intent(MainActivity.this,HomeActivity.class));

                    }
                }.start();



    }
}
