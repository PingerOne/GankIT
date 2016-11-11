package com.pinger.gankit.model.bean;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.model.bean
 *  @文件名:   Record
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/7 0:05
 *  @描述：    记录类的bean对象
 */

import java.io.Serializable;

import io.realm.RealmObject;

public class Record extends RealmObject implements Serializable {
    public String title;
    public String pic;
    public String id;
    public long time;
}
