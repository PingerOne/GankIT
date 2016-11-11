package com.pinger.gankit.ui.fragment.gank;/*
 *  @项目名：  GankIT 
 *  @包名：    ui.fragment.ganhuo
 *  @文件名:   ResourceFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/10/29 1:58
 *  @描述：    TODO
 */

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.gank.GankResourcePresenter;
import com.pinger.gankit.ui.view.gank.GankResourceView;

import butterknife.BindView;

public class ResourceFragment extends BaseFragment {


    @BindView(R.id.gankResourceView)
    GankResourceView mGankResourceView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_gank_resource;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new GankResourcePresenter(mGankResourceView);
    }

    @Override
    protected void loadData() {
        ((GankResourcePresenter) mPresenter).onRefresh();
    }
}