package com.pinger.gankit.utils;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.utils
 *  @文件名:   BeanUtil
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/6 23:53
 *  @描述：    TODO
 */

import com.lzy.ninegrid.ImageInfo;
import com.pinger.gankit.model.bean.NewsBean;
import com.pinger.gankit.model.bean.VideoInfo;
import com.pinger.gankit.model.bean.VideoRes;
import com.pinger.gankit.model.bean.VideoType;

import java.util.ArrayList;
import java.util.List;

public class BeanUtil {
    private BeanUtil(){}

    public static VideoInfo VideoType2VideoInfo(VideoType videoType, VideoInfo videoInfo) {
        if (videoInfo == null)
            videoInfo = new VideoInfo();
        videoInfo.title = videoType.title;
        videoInfo.dataId = videoType.dataId;
        videoInfo.pic = videoType.pic;
        videoInfo.airTime = videoType.airTime;
        videoInfo.score = videoType.score;
        return videoInfo;
    }

    public static VideoRes VideoInfo2VideoRes(VideoInfo videoInfo, VideoRes videoRes) {
        if (videoRes == null)
            videoRes = new VideoRes();
        videoRes.title = StringUtil.isEmpty(videoInfo.title);
        videoRes.pic = StringUtil.isEmpty(videoInfo.pic);
        videoRes.score = StringUtil.isEmpty(videoInfo.score);
        videoRes.airTime = StringUtil.isEmpty(videoInfo.airTime);
        videoRes.pic = StringUtil.isEmpty(videoInfo.pic);
        return videoRes;
    }


    public static List<ImageInfo> ImageList2ImageInfoList(List<NewsBean.NewsImage> images) {
        ArrayList<ImageInfo> imageInfos = new ArrayList<>();
        if (images != null) {
            for (NewsBean.NewsImage image : images) {

                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(image.url);
                info.setBigImageUrl(image.url);
                imageInfos.add(info);
            }
        }
        return imageInfos;
    }
}
