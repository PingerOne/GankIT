package com.pinger.gankit.ui.fragment.gank;

/*
 *  @项目名：  GankIT 
 *  @包名：    ui.fragment.ganhuo
 *  @文件名:   WebFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/10/29 1:59
 *  @描述：    TODO
 */

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.gank.GankWebPresenter;
import com.pinger.gankit.ui.view.gank.GankWebView;

import butterknife.BindView;

public class WebFragment extends BaseFragment {

    @BindView(R.id.gankWebView)
    GankWebView mGankWebView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_gank_web;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new GankWebPresenter(mGankWebView);
    }

    @Override
    protected void loadData() {
        ((GankWebPresenter) mPresenter).onRefresh();
    }

}