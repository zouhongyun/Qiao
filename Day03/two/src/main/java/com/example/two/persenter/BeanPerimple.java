package com.example.two.persenter;

import com.example.two.bean.Good;
import com.example.two.callback.BeanCallback;
import com.example.two.model.BeanModel;
import com.example.two.view.BeanView;

import java.util.List;

public class BeanPerimple implements BeanPerinter, BeanCallback {
    private BeanModel beanModel;
    private BeanView beanView;

    public BeanPerimple(BeanModel beanModel, BeanView beanView) {
        this.beanModel = beanModel;
        this.beanView = beanView;
    }

    @Override
    public void getdata() {
        beanModel.getdata(this);

    }

    @Override
    public void OnSeccess(List<Good> goods) {
        beanView.OnSeccess(goods);
    }

    @Override
    public void OnFail(String s) {
        beanView.OnFail(s);

    }
}
