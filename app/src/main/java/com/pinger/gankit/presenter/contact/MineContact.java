package com.pinger.gankit.presenter.contact;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.contract
 *  @文件名:   MineContact
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/5 22:31
 *  @描述：    我的通信接口
 */

import com.pinger.gankit.base.BasePresenter;
import com.pinger.gankit.base.BaseView;
import com.pinger.gankit.model.bean.VideoType;

import java.util.List;

public interface MineContact {

    interface View extends BaseView<Presenter> {
        boolean isActive();

        void showContent(List<VideoType> types);
    }

    interface Presenter extends BasePresenter {

        void requestHistoryData();

        void delHistoryData();
    }
}
