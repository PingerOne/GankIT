package com.pinger.gankit.presenter.news;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.news
 *  @文件名:   NewsSocietyPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/14 13:42
 *  @描述：    TODO
 */

import com.pinger.gankit.ui.view.news.SocietyView;

public class NewsSocietyPresenter extends NewsBasePresenter {

    public NewsSocietyPresenter(SocietyView societyView) {
        super(societyView);
    }

    @Override
    protected String getChannelName() {
        return "社会焦点";
    }
}
