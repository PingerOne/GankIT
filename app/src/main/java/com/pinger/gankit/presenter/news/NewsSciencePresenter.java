package com.pinger.gankit.presenter.news;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.news
 *  @文件名:   NewsSciencePresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/14 13:40
 *  @描述：    TODO
 */

import com.pinger.gankit.ui.view.news.ScienceView;

public class NewsSciencePresenter extends NewsBasePresenter {

    public NewsSciencePresenter(ScienceView scienceView) {
        super(scienceView);
    }

    @Override
    protected String getChannelName() {
        return "互联网焦点";
    }
}
