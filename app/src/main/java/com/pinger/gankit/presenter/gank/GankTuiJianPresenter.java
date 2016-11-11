package com.pinger.gankit.presenter.gank;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.gank
 *  @文件名:   GankTuiJianPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 14:46
 *  @描述：    TODO
 */

import com.pinger.gankit.ui.view.gank.GankTuiJianView;

public class GankTuiJianPresenter extends GankBasePresenter {

    public GankTuiJianPresenter(GankTuiJianView gankTuiJianView) {
        super(gankTuiJianView);
    }

    @Override
    protected String getType() {
        return "瞎推荐";
    }
}
