package com.pinger.gankit.presenter;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter.contract
 *  @文件名:   SplashPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/4 23:03
 *  @描述：    欢迎界面的Presenter层
 */

import android.support.test.espresso.core.deps.guava.base.Preconditions;

import com.pinger.gankit.base.RxPresenter;
import com.pinger.gankit.presenter.contact.SplashContact;
import com.pinger.gankit.ui.view.SplashView;
import com.pinger.gankit.utils.RxUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;

public class SplashPresenter extends RxPresenter implements SplashContact.Presenter {


    private final SplashContact.View mSplashView;
    private static final long DEFAULT_DELAY = 2200;

    public SplashPresenter(SplashView splashView) {
        // 将Presenter传递给View
        mSplashView = Preconditions.checkNotNull(splashView);
        mSplashView.setPresenter(this);
        performSplash();
    }

    @Override
    public void performSplash() {
        // 显示视图内容
        mSplashView.showContent(getSplashData());

        // 开始跳转到MainActivity，异步观察
        startMainActivity();
    }

    private void startMainActivity() {
        Subscription rxSubscription = Observable.timer(DEFAULT_DELAY, TimeUnit.MILLISECONDS)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(aLong -> {
                    mSplashView.jump2Main();
                });
        addSubscribe(rxSubscription);
    }

    private List<String> getSplashData() {
        List<String> imgs = new ArrayList<>();
        imgs.add("file:///android_asset/a.jpg");
        imgs.add("file:///android_asset/b.jpg");
        imgs.add("file:///android_asset/c.jpg");
        imgs.add("file:///android_asset/d.jpg");
        imgs.add("file:///android_asset/e.jpg");
        return imgs;
    }
}
