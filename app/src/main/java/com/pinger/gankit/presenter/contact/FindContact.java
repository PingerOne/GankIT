package com.pinger.gankit.presenter.contact;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.contract
 *  @文件名:   FindContact
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/5 22:15
 *  @描述：    TODO
 */

import com.pinger.gankit.base.BasePresenter;
import com.pinger.gankit.base.BaseView;
import com.pinger.gankit.model.bean.VideoRes;

public interface FindContact {

    interface View extends BaseView<Presenter> {
        void showContent(VideoRes videoRes);

        boolean isActive();

        void refreshFail(String msg);

        void hideLoading();

        int getLastPage();

        void setLastPage(int page);
    }

    interface Presenter extends BasePresenter {
        void requestData();
    }
}
