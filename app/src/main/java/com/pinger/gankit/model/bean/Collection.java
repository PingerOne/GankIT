package com.pinger.gankit.model.bean;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.model.bean
 *  @文件名:   Collection
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/7 0:06
 *  @描述：    收藏集合的bean对象
 */

import java.io.Serializable;

import io.realm.RealmObject;

public class Collection extends RealmObject implements Serializable {

    public String id;
    public long time;
    public String title;
    public String pic;
    public String airTime;
    public String score;
}
