package com.pinger.gankit.presenter.gank;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.gank
 *  @文件名:   GankAppPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 14:27
 *  @描述：    TODO
 */

import com.pinger.gankit.ui.view.gank.GankAppView;

public class GankAppPresenter extends GankBasePresenter {

    public GankAppPresenter(GankAppView gankAppView) {
        super(gankAppView);
    }

    @Override
    protected String getType() {
        return "App";
    }
}
