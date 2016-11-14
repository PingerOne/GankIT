package com.pinger.gankit.model.net;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.model.net
 *  @文件名:   VideoHttpResponse
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 14:37
 *  @描述：    视频请求响应结果封装
 */

public class VideoHttpResponse<T> {

    public int code;
    public String msg;
    public T ret;
}
