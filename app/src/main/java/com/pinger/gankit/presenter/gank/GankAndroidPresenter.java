package com.pinger.gankit.presenter.gank;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.gank
 *  @文件名:   GankAndroidPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 12:24
 *  @描述：    TODO
 */

import com.pinger.gankit.ui.view.gank.GankAndroidView;
import com.pinger.gankit.ui.view.gank.GankBaseView;

public class GankAndroidPresenter extends GankBasePresenter {

    public GankAndroidPresenter(GankAndroidView gankAndroidView) {
        super(gankAndroidView);
    }

    @Override
    protected String getType() {
        return "Android";
    }
}
