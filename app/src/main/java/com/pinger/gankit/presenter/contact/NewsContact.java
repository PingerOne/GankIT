package com.pinger.gankit.presenter.contact;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.contact
 *  @文件名:   NewsContact
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/13 19:03
 *  @描述：    新闻通信接口
 */

import com.pinger.gankit.base.BasePresenter;
import com.pinger.gankit.base.BaseView;
import com.pinger.gankit.model.bean.NewsBean;

import java.util.List;

public interface NewsContact {

    interface View extends BaseView<Presenter> {
        boolean isActive();

        void showContent(List<NewsBean.ContentList> newsList);

        void showMoreContent(List<NewsBean.ContentList> newsMoreList);

        void showError(String msg);

        void refreshFailed(String msg);

        void loadMoreFailed(String msg);

    }


    interface Presenter extends BasePresenter {
        void onRefresh();

        void loadMore();
    }
}
