package com.pinger.gankit.model.net;


import com.pinger.gankit.model.bean.NewsBean;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.model.net
 *  @文件名:   NewsApis
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 16:38
 *  @描述：    新闻数据API接口
 */

public interface NewsApis {

    //    apikey = 75e0aecf9a0d887adf328cb770f3a8ea
    String HOST = "http://apis.baidu.com/";

    /***
     * 请求参数(header) :  apikey

     请求参数(urlParam) :
     参数名         类型	必填	参数位置	描述	                    默认值
     channelId    string    否	  urlParam    新闻频道id，必须精确匹配      5572a109b3cdc86cf39001db
     channelName  string	否	  urlParam    新闻频道名称，可模糊匹配      国内最新
     title        string	否	  urlParam    新闻标题，模糊匹配            上市
     page         string	否	  urlParam    页数，默认1。每页最多20条记录。1
     needContent  string	否	  urlParam   是否需要返回正文，1为需要，     0
     其他为不需要
     needHtml     string	否	  urlParam   是否需要返回正文的html格式，    0
     1为需要，其他为不需要
     *
     * */


//    http://apis.baidu.com/showapi_open_bus/channel_news/search_news?channelName=国内最新&page=1

    /**
     * 获取新闻数据
     *
     * @param channelName
     * @param page
     * @return
     */
    @Headers("apikey:75e0aecf9a0d887adf328cb770f3a8ea")
    @GET("showapi_open_bus/channel_news/search_news")
    Observable<NewsHttpResponse<NewsBean>> getNewsList(@Query("channelName") String channelName, @Query("page") int page);
}
