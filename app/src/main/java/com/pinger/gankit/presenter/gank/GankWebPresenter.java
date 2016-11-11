package com.pinger.gankit.presenter.gank;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.gank
 *  @文件名:   GankWebPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 14:49
 *  @描述：    TODO
 */

import com.pinger.gankit.ui.view.gank.GankWebView;

public class GankWebPresenter extends GankBasePresenter {

    public GankWebPresenter(GankWebView gankWebView) {
        super(gankWebView);
    }

    @Override
    protected String getType() {
        return "前端";
    }
}
