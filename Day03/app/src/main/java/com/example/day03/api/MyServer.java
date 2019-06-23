package com.example.day03.api;

import com.example.day03.bean.ListBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.GET;

public interface MyServer {
    String URL="http://106.13.63.54:8080/";
    @GET("sys/gc.json")
    Observable<ListBean> get();
}
