package com.pinger.gankit.presenter;

import android.support.test.espresso.core.deps.guava.base.Preconditions;

import com.pinger.gankit.base.RxPresenter;
import com.pinger.gankit.manager.RequestManager;
import com.pinger.gankit.model.bean.GankBean;
import com.pinger.gankit.model.net.GankHttpResponse;
import com.pinger.gankit.presenter.contact.FuliContact;
import com.pinger.gankit.ui.view.FuliView;
import com.pinger.gankit.utils.RxUtil;
import com.pinger.gankit.utils.StringUtil;

import java.util.List;

import rx.Subscription;
import rx.functions.Action1;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter
 *  @文件名:   FuliPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/12 0:05
 *  @描述：    TODO
 */

public class FuliPresenter extends RxPresenter implements FuliContact.Presenter {

    private final FuliView mFuliView;
    private int mPage;

    public FuliPresenter(FuliView fuliView) {
        this.mFuliView = Preconditions.checkNotNull(fuliView);
        mFuliView.setPresenter(this);
    }

    @Override
    public void onRefresh() {
        mPage = 0;
        getGankData();
    }

    @Override
    public void loadMore() {
        mPage++;
        getGankData();
    }

    public void getGankData() {
        Subscription rxSubscription = RequestManager.getGankApis().getGankList("福利", 30, mPage)
                .compose(RxUtil.<GankHttpResponse<List<GankBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankBean>>handleGankResult())
                .subscribe(new Action1<List<GankBean>>() {
                    @Override
                    public void call(List<GankBean> gankBeanList) {
                        if (gankBeanList != null) {
                            if (mFuliView.isActive()) {
                                if (mPage == 1) {
                                    mFuliView.showContent(gankBeanList);
                                } else {
                                    mFuliView.showMoreContent(gankBeanList);
                                }
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (mPage > 1) {
                            mPage--;
                        }
                        mFuliView.refreshFailed(StringUtil.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }
}
