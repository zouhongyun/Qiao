package com.example.day03.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Good {
    @Id
    private Long id;
    private String img;
    @Generated(hash = 655191128)
    public Good(Long id, String img) {
        this.id = id;
        this.img = img;
    }
    @Generated(hash = 2016981037)
    public Good() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
}
