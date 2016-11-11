package com.pinger.gankit.presenter;

import android.support.test.espresso.core.deps.guava.base.Preconditions;

import com.pinger.gankit.base.RxPresenter;
import com.pinger.gankit.presenter.contact.MineContact;
import com.pinger.gankit.ui.view.MineView;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter
 *  @文件名:   MinePresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/5 22:34
 *  @描述：    TODO
 */

public class MinePresenter extends RxPresenter implements MineContact.Presenter {

    public static int maxSize = 30;
    private final MineView mMineView;

    public MinePresenter(MineView mineView) {
        mMineView = Preconditions.checkNotNull(mineView);
        // 通信绑定
        mMineView.setPresenter(this);
    }

    @Override
    public void requestHistoryData() {

    }

    @Override
    public void delHistoryData() {

    }
}
