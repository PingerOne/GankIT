package com.pinger.gankit.presenter.news;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.news
 *  @文件名:   NewsMilitaryPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/14 13:38
 *  @描述：    TODO
 */

import com.pinger.gankit.ui.view.news.MilitaryView;

public class NewsMilitaryPresenter extends NewsBasePresenter {

    public NewsMilitaryPresenter(MilitaryView militaryView) {
        super(militaryView);
    }

    @Override
    protected String getChannelName() {
        return "军事焦点";
    }
}
