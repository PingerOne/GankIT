package com.pinger.gankit.presenter.gank;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.gank
 *  @文件名:   GankIOSPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 14:23
 *  @描述：    TODO
 */

import com.pinger.gankit.ui.view.gank.GankIOSView;

public class GankIOSPresenter extends GankBasePresenter {

    public GankIOSPresenter(GankIOSView gankIOSView) {
        super(gankIOSView);
    }

    @Override
    protected String getType() {
        return "iOS";
    }
}
