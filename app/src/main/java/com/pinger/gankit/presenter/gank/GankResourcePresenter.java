package com.pinger.gankit.presenter.gank;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.gank
 *  @文件名:   GankResourcePresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 14:31
 *  @描述：    TODO
 */

import com.pinger.gankit.ui.view.gank.GankResourceView;

public class GankResourcePresenter extends GankBasePresenter {

    public GankResourcePresenter(GankResourceView gankResourceView) {
        super(gankResourceView);
    }

    @Override
    protected String getType() {
        return "拓展资源";
    }
}
