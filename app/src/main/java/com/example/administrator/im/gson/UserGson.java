package com.example.administrator.im.gson;

/**
 * Created by Administrator on 2018/7/15.
 */

public class UserGson<T extends Object> {

    @Override
    public String toString() {
        return "UserGson{" +
                "msg='" + msg + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userhead='" + userhead + '\'' +
                ", createtime='" + createtime + '\'' +
                ", ime='" + ime + '\'' +
                ", location='" + location + '\'' +
                ", tel='" + tel + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", vip=" + vip +
                ", longtitude=" + longtitude +
                ", latintude=" + latintude +
                ", active=" + active +
                '}';
    }

    /**
     * msg : 200
     * id : 3
     * username : 125
     * password : 123
     * userhead : https://upload.jianshu.io/users/upload_avatars/6253688/ced0be7f-2217-4cde-8377-983396e02ee1.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240
     * createtime : 2018-07-14 18:27:30
     * ime : 小米2
     * location : 南方大区
     */

    private String msg;
    private int id;
    private String username;
    private String password;
    private String userhead;
    private String createtime;
    private String ime;
    private String location;
    private String tel;
    private int sex;
    private int age;
    private int vip;
    private double longtitude;
    private double latintude;
    T active;

    public T getActive() {
        return active;
    }

    public void setActive(T active) {
        this.active = active;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatintude() {
        return latintude;
    }

    public void setLatintude(double latintude) {
        this.latintude = latintude;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserhead() {
        return userhead;
    }

    public void setUserhead(String userhead) {
        this.userhead = userhead;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
