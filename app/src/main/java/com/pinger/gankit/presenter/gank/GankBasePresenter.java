package com.pinger.gankit.presenter.gank;

import com.pinger.gankit.base.RxPresenter;
import com.pinger.gankit.manager.RequestManager;
import com.pinger.gankit.presenter.contact.GankContact;
import com.pinger.gankit.ui.view.gank.GankBaseView;
import com.pinger.gankit.utils.RxUtil;
import com.pinger.gankit.utils.StringUtil;

import rx.Subscription;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter
 *  @文件名:   GankBasePresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 10:17
 *  @描述：    由于gank数据的条目基本都是一样的，所以把RecyclerView进行了抽取
 */

public abstract class GankBasePresenter extends RxPresenter implements GankContact.Presenter {


    private int mPage;
    private final GankBaseView mGankView;
    protected String mType;
    private static final int PAGENUM = 30;

    public GankBasePresenter(GankBaseView gankView) {
        this.mGankView = gankView;
        // 绑定
        mGankView.setPresenter(this);
        mType = getType();
    }

    protected abstract String getType();

    @Override
    public void onRefresh() {
        mPage = 0;
        getGankData(mType);
    }


    @Override
    public void loadMore() {
        mPage++;
        getGankData(mType);
    }


    private void getGankData(String type) {
        Subscription rxSubscription = RequestManager.getGankApis().getGankList(type, PAGENUM, mPage)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleGankResult())
                .subscribe(gankBeanList -> {
                    if (gankBeanList != null) {
                        if (mGankView.isActive()) {
                            if (mPage == 1) {
                                mGankView.showContent(gankBeanList);
                            } else {
                                mGankView.showMoreContent(gankBeanList);
                            }
                        }
                    }
                }, throwable -> {
                    if (mPage > 1) {
                        mPage--;
                    }
                    mGankView.refreshFailed(StringUtil.getErrorMsg(throwable.getMessage()));
                });
        addSubscribe(rxSubscription);
    }
}
