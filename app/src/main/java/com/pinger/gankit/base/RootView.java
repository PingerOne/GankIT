package com.pinger.gankit.base;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.base
 *  @文件名:   RootView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/1 18:48
 *  @描述：    顶层View
 */


import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class RootView<T extends BasePresenter> extends LinearLayout {
    protected boolean mActive; // 判断Activity是否被销毁
    protected Context mContext;
    protected Unbinder unbinder;
    protected T mPresenter;

    /**
     * 获取布局参数
     */
    protected abstract void getLayout();


    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化事件
     */
    protected abstract void initEvent();

    protected void init() {
        mContext = getContext();
        getLayout();
    unbinder = ButterKnife.bind(this);
    // 激活Activity
    mActive = true;
    initView();
    initEvent();
}

    public RootView(Context context) {
        super(context);
        init();
    }

    public RootView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mPresenter != null)
            mPresenter.attachView(this);
        mActive = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mPresenter != null)
            mPresenter.detachView();
        mActive = false;
        unbinder.unbind();
        mContext = null;
    }
}