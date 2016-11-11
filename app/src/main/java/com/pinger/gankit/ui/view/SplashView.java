package com.pinger.gankit.ui.view;

import android.content.Context;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.pinger.gankit.R;
import com.pinger.gankit.ui.activity.SplashActivity;
import com.pinger.gankit.base.RootView;
import com.pinger.gankit.manager.ImageManager;
import com.pinger.gankit.presenter.contact.SplashContact;
import com.pinger.gankit.utils.JumpUtil;
import com.pinger.gankit.utils.StringUtil;

import java.util.List;

import butterknife.BindView;

/*
 *  @项目名：  GankIT 
 *  @包名：    ui.view
 *  @文件名:   SplashView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/4 23:05
 *  @描述：    欢迎界面的视图
 */

public class SplashView extends RootView<SplashContact.Presenter> implements SplashContact.View {
    @BindView(R.id.splash_bg)
    ImageView mSplashBg;

    public SplashView(Context context) {
        this(context, null);
    }

    public SplashView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setPresenter(SplashContact.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.view_splash, this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void showContent(List<String> data) {
        if (data != null) {
            int page = StringUtil.getRandomNumber(0, data.size() - 1);
            ImageManager.load(mContext, data.get(page), mSplashBg);
            // 放大动画
            mSplashBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        }
    }

    @Override
    public void jump2Main() {
        JumpUtil.go2MainActivity(mContext);
        // 跳转动画
        ((SplashActivity) mContext).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
