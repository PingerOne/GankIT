package com.pinger.gankit.ui.activity;

import android.os.Bundle;

import com.pinger.gankit.R;
import com.pinger.gankit.base.SwipeBackActivity;
import com.pinger.gankit.presenter.FuliPresenter;
import com.pinger.gankit.ui.view.FuliView;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.activity
 *  @文件名:   FuliActivity
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 21:21
 *  @描述：    TODO
 */
public class FuliActivity extends SwipeBackActivity {

    @BindView(R.id.fuliView)
    FuliView mFuliView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuli);
        unbinder = ButterKnife.bind(this);
        mPresenter = new FuliPresenter(mFuliView);
    }
}
