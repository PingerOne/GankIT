package com.pinger.gankit.presenter.gank;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter
 *  @文件名:   GankAllPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 11:11
 *  @描述：    TODO
 */

import com.pinger.gankit.ui.view.gank.GankAllView;

public class GankAllPresenter extends GankBasePresenter {

    public GankAllPresenter(GankAllView gankAllView) {
        super(gankAllView);
    }

    @Override
    protected String getType() {
        return "all";
    }
}
