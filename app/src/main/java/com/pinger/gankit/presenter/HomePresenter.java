package com.pinger.gankit.presenter;

import android.support.annotation.NonNull;
import android.support.test.espresso.core.deps.guava.base.Preconditions;

import com.pinger.gankit.base.RxPresenter;
import com.pinger.gankit.manager.RequestManager;
import com.pinger.gankit.model.bean.VideoRes;
import com.pinger.gankit.model.net.VideoHttpResponse;
import com.pinger.gankit.presenter.contact.HomeContact;
import com.pinger.gankit.ui.view.MainView;
import com.pinger.gankit.utils.RxUtil;
import com.pinger.gankit.utils.StringUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import rx.Subscription;
import rx.functions.Action1;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter
 *  @文件名:   HomeContact
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/2 20:10
 *  @描述：    Home主页的Presenter层
 */

public class HomePresenter extends RxPresenter implements HomeContact.Presenter {
    HomeContact.View mView;
    int page = 0;

    public HomePresenter(@NonNull HomeContact.View view) {
        mView = Preconditions.checkNotNull(view);
        mView.setPresenter(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onRefresh() {
        page = 0;
        getPageHomeInfo();
    }

    @Subscriber(tag = MainView.Banner_Stop)
    public void stopBanner(boolean stop) {
        mView.stopBanner(stop);
    }

    public void getPageHomeInfo() {
        Subscription rxSubscription = RequestManager.getVideoApi().getHomePage()
                .compose(RxUtil.<VideoHttpResponse<VideoRes>>rxSchedulerHelper())
                .compose(RxUtil.<VideoRes>handleResult())
                .subscribe(new Action1<VideoRes>() {
                    @Override
                    public void call(final VideoRes res) {
                        if (res != null) {
                            if (mView.isActive()) {
                                mView.showContent(res);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.refreshFail(StringUtil.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }
}
