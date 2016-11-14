package com.pinger.gankit.model.bean;

import com.google.gson.annotations.SerializedName;

/*
 *  @项目名：  GankIT
 *  @包名：    com.pinger.gankit.model.net
 *  @文件名:   VideoResult
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 14:42
 *  @描述：    请求视频资源结果
 */

public class VideoResult {
    public String code;
    public String msg;
    public
    @SerializedName("ret")
    VideoRes ret;
}
