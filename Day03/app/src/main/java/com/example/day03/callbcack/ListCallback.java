package com.example.day03.callbcack;

import com.example.day03.bean.ListBean;

public interface ListCallback {
    void ListSeccess(ListBean listBean);
    void ListFail(String s);
}
