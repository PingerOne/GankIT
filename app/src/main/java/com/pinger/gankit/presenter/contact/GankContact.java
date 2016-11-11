package com.pinger.gankit.presenter.contact;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.contact
 *  @文件名:   GankContact
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 10:11
 *  @描述：    干货的通信接口
 */

import com.pinger.gankit.base.BasePresenter;
import com.pinger.gankit.base.BaseView;
import com.pinger.gankit.model.bean.GankBean;

import java.util.List;

public interface GankContact {

    interface View extends BaseView<Presenter> {
        boolean isActive();

        void showContent(List<GankBean> gankBeanList);

        void showMoreContent(List<GankBean> gankBeanMoreList);

        void showError(String msg);

        void refreshFailed(String msg);

        void loadMoreFailed(String msg);

    }


    interface Presenter extends BasePresenter {
        void onRefresh();

        void loadMore();
    }
}
