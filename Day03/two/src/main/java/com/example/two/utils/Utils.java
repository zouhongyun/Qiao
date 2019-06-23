package com.example.two.utils;

import com.example.two.App;
import com.example.two.bean.Good;
import com.example.two.dao.DaoMaster;
import com.example.two.dao.DaoSession;
import com.example.two.dao.GoodDao;

import java.util.List;

public class Utils {
     private final GoodDao goodDao;

         private Utils(){
             DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getApp(), "zh.db");
             DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
             DaoSession daoSession = daoMaster.newSession();
             goodDao = daoSession.getGoodDao();
         };
         private static Utils ut;
         public static Utils utt(){
             if(ut==null){
                 synchronized (Utils.class){
                     if (ut==null){
                         ut=new Utils();
                     }
                 }
             }
             return ut;
         }
         public void insert(List<Good> good){
             List<Good> list = goodDao.queryBuilder().list();
             if (list.size()==0) {
                 goodDao.insertInTx(good);

             }
         }
    public void insert(Good good){
             boolean zh = zh(good);
             if (zh) {
                    goodDao.insert(good);
                }
    }
         public List<Good> select(){
             return goodDao.queryBuilder().list();
         }
         public void delete(Good good){
             boolean zh = zh(good);
             if (zh) {
                 goodDao.delete(good);
             }
         }
         public void update(Good good){
             boolean zh = zh(good);
             if (zh) {
                 goodDao.update(good);
             }
         }
         public boolean zh(Good good){
             List<Good> list = goodDao.queryBuilder().where(GoodDao.Properties.Img.eq(good.getImg())).list();
             if (list.size()>0) {
                 return true;
             }
             return false;
         }
}
