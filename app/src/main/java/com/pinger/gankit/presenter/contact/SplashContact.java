package com.pinger.gankit.presenter.contact;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.contract
 *  @文件名:   SplashContact
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/4 22:58
 *  @描述：    欢迎界面的通信接口
 */

import com.pinger.gankit.base.BasePresenter;
import com.pinger.gankit.base.BaseView;

import java.util.List;

public interface SplashContact {

    interface View extends BaseView<Presenter> {
        boolean isActive();

        void showContent(List<String> data);

        void jump2Main();
    }

    interface Presenter extends BasePresenter {
        void performSplash();
    }
}
