package com.pinger.gankit.presenter.contact;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter
 *  @文件名:   MainContact
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/1 19:47
 *  @描述：    MainActivity中View和Presenter的通信接口
 */

import com.pinger.gankit.base.BasePresenter;
import com.pinger.gankit.base.BaseView;

public interface MainContact {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}