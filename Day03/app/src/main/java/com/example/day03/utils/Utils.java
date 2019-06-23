package com.example.day03.utils;

import com.example.day03.App;
import com.example.day03.bean.Good;
import com.example.day03.dao.DaoMaster;
import com.example.day03.dao.DaoSession;
import com.example.day03.dao.GoodDao;

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
         public long insert(Good good){
             boolean zh = zh(good);
             if (!zh) {
                 return goodDao.insert(good);
             }else {
                 return -1;
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
