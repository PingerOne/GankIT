package com.pinger.gankit.model.net;/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.model.net
 *  @文件名:   ApiException
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 15:25
 *  @描述：    请求出现异常
 */

public class ApiException extends Exception {
    public ApiException(String msg) {
        super(msg);
    }
}
