package com.pinger.gankit.ui.fragment.gank;

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.gank.GankIOSPresenter;
import com.pinger.gankit.ui.view.gank.GankIOSView;

import butterknife.BindView;

/*
 *  @项目名：  GankIT 
 *  @包名：    ui.fragment.content
 *  @文件名:   IOSFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/10/26 0:39
 *  @描述：    TODO
 */

public class IOSFragment extends BaseFragment {

    @BindView(R.id.gankIosView)
    GankIOSView mGankIosView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_gank_ios;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new GankIOSPresenter(mGankIosView);
    }

    @Override
    protected void loadData() {
        ((GankIOSPresenter) mPresenter).onRefresh();
    }
}
