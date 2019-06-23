package com.example.two.view;

import com.example.two.bean.Good;

import java.util.List;

public interface BeanView {
    void OnSeccess(List<Good> goods);
    void OnFail(String s);
}
