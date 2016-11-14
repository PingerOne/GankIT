package com.pinger.gankit.base;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.base
 *  @文件名:   RxPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/2 20:21
 *  @描述：    基于Rx封装Presenter，管理订阅者的生命周期
 */

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class RxPresenter<T> implements BasePresenter<T> {
    protected T mView;
    protected CompositeSubscription mCompositeSubscription;

    /**
     * 不订阅
     */
    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }


    /**
     * 添加订阅者
     *
     * @param subscription
     */
    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
