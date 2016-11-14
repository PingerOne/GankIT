package com.pinger.gankit.presenter;

import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.util.DisplayMetrics;

import com.pinger.gankit.base.RxPresenter;
import com.pinger.gankit.manager.RequestManager;
import com.pinger.gankit.model.bean.GankBean;
import com.pinger.gankit.presenter.contact.FuliContact;
import com.pinger.gankit.ui.view.FuliView;
import com.pinger.gankit.utils.RxUtil;
import com.pinger.gankit.utils.StringUtil;

import java.util.List;

import rx.Subscription;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter
 *  @文件名:   FuliPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/12 0:05
 *  @描述：    福利的Presenter接口实现，加载数据
 */

public class FuliPresenter extends RxPresenter implements FuliContact.Presenter {

    public static final int PAGENUM = 15;
    private final FuliView mFuliView;
    private int mPage;

    public FuliPresenter(FuliView fuliView) {
        this.mFuliView = Preconditions.checkNotNull(fuliView);
        mFuliView.setPresenter(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        getGankData();
    }

    @Override
    public void loadMore() {
        mPage++;
        getGankData();
    }


    /**
     * 获取数据
     */
    private void getGankData() {
        Subscription rxSubscription = RequestManager.getGankApis().getGankList("福利", PAGENUM, mPage)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleGankResult())
                .subscribe(gankBeanList -> {
                    if (gankBeanList != null) {
                        if (mFuliView.isActive()) {
                            // 设置随机高度
                            setHeight(gankBeanList);
                            if (mPage == 1) {
                                mFuliView.showContent(gankBeanList);
                            } else {
                                mFuliView.showMoreContent(gankBeanList);
                            }
                        }
                    }
                }, throwable -> {
                    if (mPage > 1) {
                        mPage--;
                    }
                    mFuliView.refreshFailed(StringUtil.getErrorMsg(throwable.getMessage()));
                });
        addSubscribe(rxSubscription);
    }

    /**
     * 设置图片的随机高度
     *
     * @param list
     */
    private void setHeight(List<GankBean> list) {
        DisplayMetrics dm = mFuliView.getContext().getResources().getDisplayMetrics();
        //宽度为屏幕宽度一半
        int width = dm.widthPixels / 2;
        for (GankBean gankBean : list) {
            //随机的高度
            gankBean.setHeight(width * StringUtil.getRandomNumber(3, 5) / 3);
        }
    }
}
