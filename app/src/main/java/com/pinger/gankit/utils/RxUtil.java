package com.pinger.gankit.utils;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.utils
 *  @文件名:   RxUtil
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 15:24
 *  @描述：    Rx管理请求工具类
 */

import android.text.TextUtils;

import com.pinger.gankit.model.net.ApiException;
import com.pinger.gankit.model.net.GankHttpResponse;
import com.pinger.gankit.model.net.NewsHttpResponse;
import com.pinger.gankit.model.net.VideoHttpResponse;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxUtil {
    private RxUtil(){}

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<VideoHttpResponse<T>, T> handleResult() {   //compose判断结果
        return httpResponseObservable -> httpResponseObservable.flatMap(new Func1<VideoHttpResponse<T>, Observable<T>>() {
            @Override
            public Observable<T> call(VideoHttpResponse<T> videoHttpResponse) {
                if (videoHttpResponse.code == 200) {
                    return createData(videoHttpResponse.ret);
                } else if (!TextUtils.isEmpty(videoHttpResponse.msg)) {
                    return Observable.error(new ApiException("*" + videoHttpResponse.msg));
                } else {
                    return Observable.error(new ApiException("*" + "服务器返回error"));
                }
            }
        });
    }


    /**
     * 干货数据的返回结果
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<GankHttpResponse<T>, T> handleGankResult() {   //compose判断结果
        return httpResponseObservable -> httpResponseObservable.flatMap(new Func1<GankHttpResponse<T>, Observable<T>>() {
            @Override
            public Observable<T> call(GankHttpResponse<T> tGankHttpResponse) {
                if (!tGankHttpResponse.getError()) {
                    return createData(tGankHttpResponse.getResults());
                } else {
                    return Observable.error(new ApiException("服务器返回error"));
                }
            }
        });
    }


    /**
     * 返回新闻数据结果
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<NewsHttpResponse<T>, T> handleNewsResult() {   //compose判断结果
        return httpResponseObservable -> httpResponseObservable.flatMap(new Func1<NewsHttpResponse<T>, Observable<T>>() {
            @Override
            public Observable<T> call(NewsHttpResponse<T> tNewsHttpResponse) {
                if (!tNewsHttpResponse.error) {
                    return createData(tNewsHttpResponse.showapi_res_body);
                } else {
                    return Observable.error(new ApiException("服务器返回error"));
                }
            }
        });
    }

    /**
     * 生成Observable(观察者)
     *
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

}
