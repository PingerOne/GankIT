package com.pinger.gankit.presenter;

import android.support.test.espresso.core.deps.guava.base.Preconditions;

import com.pinger.gankit.base.RxPresenter;
import com.pinger.gankit.manager.RequestManager;
import com.pinger.gankit.presenter.contact.FindContact;
import com.pinger.gankit.ui.view.FindView;
import com.pinger.gankit.utils.RxUtil;
import com.pinger.gankit.utils.StringUtil;
import com.pinger.gankit.utils.SystemUtil;

import rx.Subscription;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter
 *  @文件名:   FindPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/5 22:27
 *  @描述：    发现的Presenter层
 */

public class FindPresenter extends RxPresenter implements FindContact.Presenter {

    private final FindView mFindView;
    private final String catalogId = "402834815584e463015584e53843000b";

    private int maxPage = 90;
    private int minPage = 1;


    public FindPresenter(FindView findView) {
        mFindView = Preconditions.checkNotNull(findView);
        // 绑定
        mFindView.setPresenter(this);
    }

    @Override
    public void requestData() {
        getNextVideo();
    }

    private void getNextVideo() {
        Subscription rxSubscription = RequestManager.getVideoApi().getVideoList(catalogId, getNextPage() + "")
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleResult())
                .subscribe(res -> {
                            if (res != null) {
                                if (mFindView.isActive()) {
                                    mFindView.showContent(res);
                                }
                            }
                        }, throwable -> mFindView.refreshFail(StringUtil.getErrorMsg(throwable.getMessage())), () -> {
                            if (mFindView.isActive())
                                mFindView.hideLoading();
                        }
                );

        addSubscribe(rxSubscription);
    }

    /**
     * 随机获取下一页
     *
     * @return
     */
    private int getNextPage() {
        int page = mFindView.getLastPage();
        if (SystemUtil.isNetworkConnected()) {
            page = StringUtil.getRandomNumber(minPage, maxPage);
            mFindView.setLastPage(page);
        }
        return page;
    }
}
