package com.pinger.gankit.model.bean;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 *  @项目名：  GankIT
 *  @包名：    com.pinger.gankit.model.net
 *  @文件名:   VideoRes
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 14:35
 *  @描述：    视频资源
 */
public class VideoRes {
    public
    @SerializedName("list")
    List<VideoType> list;
    public String title;
    public String score;
    public String videoType;
    public String region;
    public String airTime;
    public String director;
    public String actors;
    public String pic;
    public String description;
    public String smoothURL;
    public String SDURL;
    public String HDURL;

    public String getVideoUrl() {
        if (!TextUtils.isEmpty(HDURL))
            return HDURL;
        else if (!TextUtils.isEmpty(SDURL))
            return SDURL;
        else if (!TextUtils.isEmpty(smoothURL))
            return smoothURL;
        else
            return "";
    }
}
