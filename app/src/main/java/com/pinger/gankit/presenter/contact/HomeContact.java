package com.pinger.gankit.presenter.contact;

import com.pinger.gankit.base.BasePresenter;
import com.pinger.gankit.base.BaseView;
import com.pinger.gankit.model.bean.VideoRes;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.contract
 *  @文件名:   HomeContact
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/2 20:12
 *  @描述：    主页的通信
 */

public interface HomeContact {

    public interface View extends BaseView<Presenter> {
        /**
         * 拿着数据去显示
         *
         * @param videoRes
         */
        void showContent(VideoRes videoRes);

        /**
         * 显示错误视图
         *
         * @param msg
         */
        void refreshFail(String msg);

        /**
         * 是否激活
         *
         * @return
         */
        boolean isActive();

        /**
         * 是否停止轮播
         *
         * @param stop
         */
        void stopBanner(boolean stop);

    }

    public interface Presenter extends BasePresenter {
        /**
         * 刷新数据
         */
        void onRefresh();
    }

}
