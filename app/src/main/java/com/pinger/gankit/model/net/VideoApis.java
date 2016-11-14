package com.pinger.gankit.model.net;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.model.net
 *  @文件名:   VideoApis
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 14:26
 *  @描述：    视频API接口
 */

import com.pinger.gankit.model.bean.VideoRes;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface VideoApis {


    String HOST = "http://api.svipmovie.com/front/";

    /**
     * 首页
     *
     * @return
     */
    @GET("homePageApi/homePage.do")
    Observable<VideoHttpResponse<VideoRes>> getHomePage();

    /**
     * 影片详情
     *
     * @param mediaId 影片id
     * @return
     */
    @GET("videoDetailApi/videoDetail.do")
    Observable<VideoHttpResponse<VideoRes>> getVideoInfo(@Query("mediaId") String mediaId);

    /**
     * 影片分类列表
     *
     * @param catalogId
     * @param pnum
     * @return
     */
    @GET("columns/getVideoList.do")
    Observable<VideoHttpResponse<VideoRes>> getVideoList(@Query("catalogId") String catalogId, @Query("pnum") String pnum);

    /**
     * 获取评论列表
     *
     * @param mediaId
     * @param pnum
     * @return
     */
    @GET("Commentary/getCommentList.do")
    Observable<VideoHttpResponse<VideoRes>> getCommentList(@Query("mediaId") String mediaId, @Query("pnum") String pnum);
}

