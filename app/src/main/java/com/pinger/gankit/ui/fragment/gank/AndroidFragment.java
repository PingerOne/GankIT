package com.pinger.gankit.ui.fragment.gank;

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.gank.GankAndroidPresenter;
import com.pinger.gankit.ui.view.gank.GankAndroidView;

import butterknife.BindView;

/*
 *  @项目名：  GankIT 
 *  @包名：    ui.fragment.content
 *  @文件名:   AndroidFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/10/26 0:38
 *  @描述：    TODO
 */

public class AndroidFragment extends BaseFragment {


    @BindView(R.id.gankAndroidView)
    GankAndroidView mGankAndroidView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_gank_angroid;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new GankAndroidPresenter(mGankAndroidView);
    }

    @Override
    protected void loadData() {
        ((GankAndroidPresenter) mPresenter).onRefresh();
    }
}
