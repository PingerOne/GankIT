package com.pinger.gankit.presenter.contact;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.contract
 *  @文件名:   VideoListContact
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/8 16:36
 *  @描述：    视频列表通信接口
 */

import com.pinger.gankit.base.BasePresenter;
import com.pinger.gankit.base.BaseView;
import com.pinger.gankit.model.bean.VideoType;

import java.util.List;

public interface VideoListContact {

    interface View extends BaseView<Presenter> {
        boolean isActive();

        void showTitle(String title);

        void refreshFail(String msg);

        void loadMoreFail(String msg);

        void showContent(List<VideoType> typeList);

        void showMoreContent(List<VideoType> moreTypeList);
    }

    interface Presenter extends BasePresenter {
        void onRefresh();

        void loadMore();
    }
}
