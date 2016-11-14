package com.pinger.gankit.presenter.news;

import android.support.test.espresso.core.deps.guava.base.Preconditions;

import com.pinger.gankit.base.RxPresenter;
import com.pinger.gankit.manager.RequestManager;
import com.pinger.gankit.presenter.contact.NewsContact;
import com.pinger.gankit.ui.view.news.NewsBaseView;
import com.pinger.gankit.utils.RxUtil;
import com.pinger.gankit.utils.StringUtil;

import rx.Subscription;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter
 *  @文件名:   NewsBasePresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/13 19:06
 *  @描述：    TODO
 */

public abstract class NewsBasePresenter extends RxPresenter implements NewsContact.Presenter {
    private final NewsBaseView mNewsBaseView;
    private int mPage;

    public NewsBasePresenter(NewsBaseView newsBaseView) {
        this.mNewsBaseView = Preconditions.checkNotNull(newsBaseView);
        mNewsBaseView.setPresenter(this);

        // TODO：重构：根据不同的View来赋值不同的频道，不需要建立子类去
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        requestData();
    }

    @Override
    public void loadMore() {
        mPage++;
        requestData();
    }


    /**
     * 请求数据
     */
    private void requestData() {
        Subscription rxSubscription = RequestManager.getNewsApis().getNewsList(getChannelName(), mPage)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleNewsResult())
                .subscribe(newsBean -> {
                    if (newsBean != null) {
                        if (mNewsBaseView.isActive()) {
                            if (mPage == 1) {
                                mNewsBaseView.showContent(newsBean.pagebean.contentlist);
                            } else {
                                mNewsBaseView.showMoreContent(newsBean.pagebean.contentlist);
                            }
                        }
                    }
                }, throwable -> {
                    if (mPage > 1) {
                        mPage--;
                    }
                    mNewsBaseView.refreshFailed(StringUtil.getErrorMsg(throwable.getMessage()));
                });
        addSubscribe(rxSubscription);
    }

    protected abstract String getChannelName();
}
