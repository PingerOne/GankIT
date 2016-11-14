package com.pinger.gankit.manager;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.manager
 *  @文件名:   RequestManager
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 14:25
 *  @描述：    网络请求管理器
 */

import com.pinger.gankit.app.Constant;
import com.pinger.gankit.model.net.GankApis;
import com.pinger.gankit.model.net.NewsApis;
import com.pinger.gankit.model.net.VideoApis;
import com.pinger.gankit.utils.SystemUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {

    private static OkHttpClient okHttpClient = null;
    private static VideoApis videoApi;
    private static GankApis gankApis;
    private static NewsApis newsApis;

    /**
     * 获取视频API
     *
     * @return
     */
    public static VideoApis getVideoApi() {
        initOkHttp();
        if (videoApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(VideoApis.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            videoApi = retrofit.create(VideoApis.class);
        }
        return videoApi;
    }

    /**
     * 获取干货API
     *
     * @return
     */
    public static GankApis getGankApis() {
        initOkHttp();
        if (gankApis == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(GankApis.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            gankApis = retrofit.create(GankApis.class);
        }
        return gankApis;
    }


    /**
     * 获取新闻API
     *
     * @return
     */
    public static NewsApis getNewsApis() {
        initOkHttp();

        if (newsApis == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(NewsApis.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            newsApis = retrofit.create(NewsApis.class);
        }
        return newsApis;
    }


    /**
     * 初始化Okhttp
     */
    private static void initOkHttp() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            File cacheFile = new File(Constant.PATH_CACHE);
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
            Interceptor cacheInterceptor = chain -> {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                int tryCount = 0;
                Response response = chain.proceed(request);
                while (!response.isSuccessful() && tryCount < 3) {
                    tryCount++;
                    response = chain.proceed(request);
                }

                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            };

            //设置缓存
            builder.addNetworkInterceptor(cacheInterceptor);
            builder.addInterceptor(cacheInterceptor);
            builder.cache(cache);

            //设置超时
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);

            //错误重连
            builder.retryOnConnectionFailure(true);
            okHttpClient = builder.build();
        }
    }
}
