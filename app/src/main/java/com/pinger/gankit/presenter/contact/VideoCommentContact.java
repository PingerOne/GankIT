package com.pinger.gankit.presenter.contact;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.contract
 *  @文件名:   VideoCommentContact
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/8 14:29
 *  @描述：    视频评论的通信接口
 */

import com.pinger.gankit.base.BasePresenter;
import com.pinger.gankit.base.BaseView;
import com.pinger.gankit.model.bean.VideoType;

import java.util.List;

public interface VideoCommentContact {

    interface View extends BaseView<Presenter> {
        boolean isActive();

        void refreshFail(String msg);

        void showContent(List<VideoType> types);

        void showMoreContent(List<VideoType> moreTypes);
    }

    interface Presenter extends BasePresenter {

        void onRefresh();

        void loadMore();
    }

}
