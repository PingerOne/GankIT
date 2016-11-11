package com.pinger.gankit.model.net;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.model.net
 *  @文件名:   GankApis
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 14:45
 *  @描述：    Gank数据API接口
 */

import com.pinger.gankit.model.bean.GankBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GankApis {

    String HOST = "http://gank.io/api/";


    /**
     分类数据: http://gank.io/api/data/数据类型/请求个数/第几页

     数据类型： 福利 | Android | iOS | 拓展资源 | 前端 | all | 瞎推荐 | App
     请求个数： 数字，大于0
     第几页：数字，大于0
     */

    /**
     * 获取干货数据
     */
    @GET("data/{type}/{num}/{page}")
    Observable<GankHttpResponse<List<GankBean>>> getGankList(@Path("type") String type, @Path("num") int num, @Path("page") int page);

}

