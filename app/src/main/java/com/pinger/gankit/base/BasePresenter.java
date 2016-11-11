package com.pinger.gankit.base;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.base
 *  @文件名:   BasePresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/1 18:30
 *  @描述：    基类Presenter
 */

public interface BasePresenter<T> {

    /**
     * 绑定View
     * @param view
     */
    void attachView(T view);

    /**
     * 解绑View
     */
    void detachView();
}