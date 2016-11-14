package com.pinger.gankit.presenter;

import android.support.annotation.NonNull;
import android.support.test.espresso.core.deps.guava.base.Preconditions;

import com.pinger.gankit.base.RxPresenter;
import com.pinger.gankit.manager.RequestManager;
import com.pinger.gankit.model.bean.VideoInfo;
import com.pinger.gankit.model.bean.VideoRes;
import com.pinger.gankit.presenter.contact.VideoDetailContact;
import com.pinger.gankit.utils.BeanUtil;
import com.pinger.gankit.utils.RxUtil;

import org.simple.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter
 *  @文件名:   VideoDetailPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/6 23:26
 *  @描述：    视频详情的Presenter层
 */

public class VideoDetailPresenter extends RxPresenter implements VideoDetailContact.Presenter {

    public final static String Refresh_Video_Info = "Refresh_Video_Info";
    public final static String Put_DataId = "Put_DataId";
    @NonNull
    private final VideoDetailContact.View mView;
    private final int WAIT_TIME = 200;
    private VideoRes result;
    private String dataId;
    private String pic;

    public VideoDetailPresenter(@NonNull VideoDetailContact.View addTaskView, VideoInfo videoInfo) {
        mView = Preconditions.checkNotNull(addTaskView);
        mView.setPresenter(this);
        mView.showContent(BeanUtil.VideoInfo2VideoRes(videoInfo, null));
        this.dataId = videoInfo.dataId;
        this.pic = videoInfo.pic;
        requestDetailData(videoInfo.dataId);
        putMediaId();
    }


    @Override
    public void requestDetailData(String dataId) {
        Subscription rxSubscription = RequestManager.getVideoApi().getVideoInfo(dataId)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleResult())
                .subscribe(res -> {
                    if (res != null) {
                        if (mView.isActive()) {
                            mView.showContent(res);
                            result = res;
                            postData();
                        }
                    }
                }, throwable -> {
                    if (mView.isActive())
                        mView.hideLoading();
                    mView.showError("数据加载失败");
                }, () -> {
                    if (mView.isActive())
                        mView.hideLoading();
                });
        addSubscribe(rxSubscription);
    }


    /**
     * 拿到数据将数据发送出去
     */
    private void postData() {
        Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(aLong -> {
                    // 视频详情的简介页需要同样的数据
                    EventBus.getDefault().post(result, Refresh_Video_Info);
                });
        addSubscribe(rxSubscription);
    }

    /**
     * 将播放视频的id发送出去
     */
    private void putMediaId() {
        Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(aLong -> {
                    // 将视频id发出去，视频评论列表需要id去查询数据
                    EventBus.getDefault().post(dataId, Put_DataId);
                });
        addSubscribe(rxSubscription);
    }
}
