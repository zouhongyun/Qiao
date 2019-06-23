package com.example.two.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Good {
    @Id
    private Long id;
    private int img;
    private String content;
    private String title;
    @Generated(hash = 891408955)
    public Good(Long id, int img, String content, String title) {
        this.id = id;
        this.img = img;
        this.content = content;
        this.title = title;
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
    public int getImg() {
        return this.img;
    }
    public void setImg(int img) {
        this.img = img;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
