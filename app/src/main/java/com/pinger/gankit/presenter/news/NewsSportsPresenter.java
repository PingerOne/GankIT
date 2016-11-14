package com.pinger.gankit.presenter.news;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.news
 *  @文件名:   NewsSportsPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/14 13:43
 *  @描述：    TODO
 */

import com.pinger.gankit.ui.view.news.NewsBaseView;

public class NewsSportsPresenter extends NewsBasePresenter {

    public NewsSportsPresenter(NewsBaseView newsBaseView) {
        super(newsBaseView);
    }

    @Override
    protected String getChannelName() {
        return "体育焦点";
    }
}
