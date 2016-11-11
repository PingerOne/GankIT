package com.pinger.gankit.base;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.base
 *  @文件名:   BaseView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/1 18:32
 *  @描述：    基类View
 */
public interface BaseView<T> {

    /**
     * 设置Presenter
     * @param presenter
     */
    void setPresenter(T presenter);


    /**
     * 显示错误信息
     * @param msg
     */
    void showError(String msg);
}