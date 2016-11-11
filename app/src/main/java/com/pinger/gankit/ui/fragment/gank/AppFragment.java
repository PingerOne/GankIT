package com.pinger.gankit.ui.fragment.gank;/*
 *  @项目名：  GankIT 
 *  @包名：    ui.fragment.ganhuo
 *  @文件名:   AppFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/10/29 1:59
 *  @描述：    TODO
 */

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.gank.GankAppPresenter;
import com.pinger.gankit.ui.view.gank.GankAppView;

import butterknife.BindView;

public class AppFragment extends BaseFragment {

    @BindView(R.id.gankAppView)
    GankAppView mGankAppView;


    @Override
    protected int getLayout() {
        return R.layout.fragment_gank_app;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new GankAppPresenter(mGankAppView);
    }

    @Override
    protected void loadData() {
        ((GankAppPresenter) mPresenter).onRefresh();
    }
}