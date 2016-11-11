package com.pinger.gankit.ui.activity;
/*
 *  @项目名：  GankIT 
 *  @包名：    ui.activity
 *  @文件名:   SplashActivity
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 15:16
 *  @描述：    欢迎界面
 */

import android.os.Bundle;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseActivity;
import com.pinger.gankit.presenter.SplashPresenter;
import com.pinger.gankit.ui.view.SplashView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.splashView)
    SplashView mSplashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        unbinder = ButterKnife.bind(this);
        // 将视图传递给Presenter
        mPresenter = new SplashPresenter(mSplashView);
    }
}
