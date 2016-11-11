package com.pinger.gankit.model.bean;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.model.bean
 *  @文件名:   SearchKey
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/7 0:06
 *  @描述：    搜索关键字的bean对象
 */

import io.realm.RealmObject;

public class SearchKey extends RealmObject {

    public String searchKey;
    public long insertTime; //插入时间
}
