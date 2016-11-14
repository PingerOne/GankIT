package com.pinger.gankit.presenter.news;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.news
 *  @文件名:   NewsTopPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/13 20:22
 *  @描述：    TODO
 */

import com.pinger.gankit.ui.view.news.NewsTopView;

public class NewsTopPresenter extends NewsBasePresenter {

    public NewsTopPresenter(NewsTopView newsTopView) {
        super(newsTopView);
    }

    @Override
    protected String getChannelName() {
        return "国内最新";
    }
}
