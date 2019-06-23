package com.example.day03.persenter;

import com.example.day03.bean.ListBean;
import com.example.day03.callbcack.ListCallback;
import com.example.day03.model.ListModel;
import com.example.day03.view.Listview;

public class ListPerimple implements ListPersenter, ListCallback {
    private ListModel listModel;
    private Listview listview;

    public ListPerimple(ListModel listModel, Listview listview) {
        this.listModel = listModel;
        this.listview = listview;
    }

    @Override
    public void getdata() {
        listModel.getdata(this);

    }

    @Override
    public void ListSeccess(ListBean listBean) {
        listview.ListSeccess(listBean);
    }

    @Override
    public void ListFail(String s) {
        listview.ListFail(s);

    }
}
