package com.example.administrator.im.entity;

/**
 * Created by Administrator on 2018/7/16.
 */

public class BannerGson {

    /**
     * id : 1
     * banner : http://111.230.18.100/img/1.png
     * url : http://sc.chinaz.com/tag_jiaoben/tupianlunbo.html
     */

    private int id;
    private String banner;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
