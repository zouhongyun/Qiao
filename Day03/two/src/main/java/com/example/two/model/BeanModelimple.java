package com.example.two.model;

import com.example.two.R;
import com.example.two.bean.Good;
import com.example.two.callback.BeanCallback;
import com.example.two.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class BeanModelimple implements BeanModel {
    @Override
    public void getdata(BeanCallback beanCallback) {
        ArrayList<Good> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Good(null, R.mipmap.ic_launcher,"邹鸿运"+i,"zhy"+i));
        }
        Utils.utt().insert(list);
        List<Good> select = Utils.utt().select();
        if (select!=null) {
            beanCallback.OnSeccess(select);
        }else {
            beanCallback.OnFail("失败");
        }
    }
}
