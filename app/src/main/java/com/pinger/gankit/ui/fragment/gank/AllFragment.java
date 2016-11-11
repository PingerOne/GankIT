package com.pinger.gankit.ui.fragment.gank;

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.gank.GankAllPresenter;
import com.pinger.gankit.ui.view.gank.GankAllView;

import butterknife.BindView;

/*
 *  @项目名：  GankIT 
 *  @包名：    ui.fragment.content
 *  @文件名:   AllFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/10/26 0:33
 *  @描述：    主页对应的Fragment
 */

public class AllFragment extends BaseFragment {

    @BindView(R.id.gankAllView)
    GankAllView mGankAllView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_gank_all;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new GankAllPresenter(mGankAllView);
    }

    @Override
    protected void loadData() {
        ((GankAllPresenter) mPresenter).onRefresh();
    }
}
