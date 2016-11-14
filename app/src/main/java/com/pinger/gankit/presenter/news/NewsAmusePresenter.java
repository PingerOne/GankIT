package com.pinger.gankit.presenter.news;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.news
 *  @文件名:   NewsAmusePresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/14 13:33
 *  @描述：    TODO
 */

import com.pinger.gankit.ui.view.news.AmuseView;

public class NewsAmusePresenter extends NewsBasePresenter {

    public NewsAmusePresenter(AmuseView amuseView) {
        super(amuseView);
    }

    @Override
    protected String getChannelName() {
        return "娱乐焦点";
    }
}
