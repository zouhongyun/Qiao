package com.example.two.callback;

import com.example.two.bean.Good;

import java.util.List;

public interface BeanCallback {
    void OnSeccess(List<Good> goods);
    void OnFail(String s);
}
