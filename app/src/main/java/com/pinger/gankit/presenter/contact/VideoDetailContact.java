package com.pinger.gankit.presenter.contact;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.contract
 *  @文件名:   VideoDetailContact
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/6 23:18
 *  @描述：    视频详情页的通信接口
 */

import com.pinger.gankit.base.BasePresenter;
import com.pinger.gankit.base.BaseView;
import com.pinger.gankit.model.bean.VideoRes;

public interface VideoDetailContact {

    interface View extends BaseView<Presenter> {
        boolean isActive();

        void showContent(VideoRes videoRes);

        // 隐藏进度
        void hideLoading();

        // 收藏
        void collected();

        void disCollect();
    }

    interface Presenter extends BasePresenter {
        // 请求数据
        void requestDetailData(String videoId);

        void insertRecord();

        void collect();
    }
}
