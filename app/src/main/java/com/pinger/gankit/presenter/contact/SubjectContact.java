package com.pinger.gankit.presenter.contact;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.contract
 *  @文件名:   SubjectContact
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/5 20:00
 *  @描述：    主题的通信接口
 */

import com.pinger.gankit.base.BasePresenter;
import com.pinger.gankit.base.BaseView;
import com.pinger.gankit.model.bean.VideoRes;

public interface SubjectContact {

    interface View extends BaseView<Presenter> {
        boolean isActive();

        void showContent(VideoRes videoRes);

        void refreshFail(String msg);

    }

    interface Presenter extends BasePresenter {
        void onRefresh();
    }
}
