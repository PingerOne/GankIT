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

public interface MineContact {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void loginSuccess();

        void loginFailed();

    }

    interface Presenter extends BasePresenter {

        void loginQQ();
    }
}
