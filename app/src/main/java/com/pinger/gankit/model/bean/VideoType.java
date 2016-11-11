package com.pinger.gankit.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 *  @项目名：  GankIT
 *  @包名：    com.pinger.gankit.model.net
 *  @文件名:   VideoType
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 14:45
 *  @描述：    视频类型
 */
public class VideoType {
    public String title;
    public String moreURL;
    public String pic;
    public String dataId;
    public String airTime;
    public String score;
    public String description;
    public String msg;
    public String phoneNumber;
    public String userPic;
    public String time;
    public String likeNum;
    public
    @SerializedName("childList")
    List<VideoInfo> childList;
}
