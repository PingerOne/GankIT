package com.pinger.gankit.presenter;

import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.text.TextUtils;

import com.pinger.gankit.base.RxPresenter;
import com.pinger.gankit.manager.RequestManager;
import com.pinger.gankit.presenter.contact.VideoCommentContact;
import com.pinger.gankit.ui.view.VideoCommentView;
import com.pinger.gankit.utils.RxUtil;
import com.pinger.gankit.utils.StringUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import rx.Subscription;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter
 *  @文件名:   VideoCommentPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/8 14:33
 *  @描述：    视频评论列表Presenter层
 */

public class VideoCommentPresenter extends RxPresenter implements VideoCommentContact.Presenter {

    private final VideoCommentContact.View mView;
    private int mPage = 1;
    private String mediaId;

    public VideoCommentPresenter(VideoCommentView commentView) {
        this.mView = Preconditions.checkNotNull(commentView);
        // 设置关联
        mView.setPresenter(this);
        EventBus.getDefault().register(this);
        // 手动刷新
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        if (!TextUtils.isEmpty(mediaId)) {
            getVideoComment(mediaId);
        }
    }

    @Override
    public void loadMore() {
        mPage++;
        if (!TextUtils.isEmpty(mediaId)) {
            getVideoComment(mediaId);
        }
    }

    /**
     * 根据视频id获取对应的评论列表
     *
     * @param mediaId
     */
    private void getVideoComment(String mediaId) {
        Subscription rxSubscription = RequestManager.getVideoApi().getCommentList(mediaId, String.valueOf(mPage))
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleResult())
                .subscribe(res -> {
                    if (res != null) {
                        if (mView.isActive()) {
                            if (mPage == 1) {
                                // 显示内容
                                mView.showContent(res.list);
                            } else {
                                // 显示更多
                                mView.showMoreContent(res.list);
                            }
                        }
                    }
                }, throwable -> {
                    if (mPage > 1) {
                        mPage--;
                    }
                    // 刷新失败
                    mView.refreshFail(StringUtil.getErrorMsg(throwable.getMessage()));
                });
        addSubscribe(rxSubscription);
    }


    @Subscriber(tag = VideoDetailPresenter.Put_DataId)
    public void getVideoId(String mediaId) {
        this.mediaId = mediaId;
        onRefresh();
    }
}
