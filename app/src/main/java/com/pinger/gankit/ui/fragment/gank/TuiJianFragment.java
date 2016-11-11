package com.pinger.gankit.ui.fragment.gank;/*
 *  @项目名：  GankIT 
 *  @包名：    ui.fragment.ganhuo
 *  @文件名:   TuiJianFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/10/29 1:59
 *  @描述：    TODO
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.gank.GankTuiJianPresenter;
import com.pinger.gankit.ui.view.gank.GankTuiJianView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TuiJianFragment extends BaseFragment {


    @BindView(R.id.gankTuijianView)
    GankTuiJianView mGankTuijianView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_gank_tuijian;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new GankTuiJianPresenter(mGankTuijianView);
    }

    @Override
    protected void loadData() {
        ((GankTuiJianPresenter)mPresenter).onRefresh();
    }
}