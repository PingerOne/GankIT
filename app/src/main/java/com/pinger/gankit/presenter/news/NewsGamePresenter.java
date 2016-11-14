package com.pinger.gankit.presenter.news;

import com.pinger.gankit.ui.view.news.GameView;

/*
 *  @项目名：  GankIT
 *  @包名：    com.pinger.gankit.presenter
 *  @文件名:   NewsGamePresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/14 13:36
 *  @描述：    TODO
 */
public class NewsGamePresenter extends NewsBasePresenter {
    public NewsGamePresenter(GameView gameView) {
        super(gameView);
    }

    @Override
    protected String getChannelName() {
        return "游戏焦点";
    }
}
