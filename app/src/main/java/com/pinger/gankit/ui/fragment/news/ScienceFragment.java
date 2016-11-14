package com.pinger.gankit.ui.fragment.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.news.NewsSciencePresenter;
import com.pinger.gankit.ui.view.news.ScienceView;

import butterknife.BindView;
import butterknife.ButterKnife;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.fragment.news
 *  @文件名:   ScienceFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/13 18:48
 *  @描述：    TODO
 */

public class ScienceFragment extends BaseFragment {
    @BindView(R.id.scienceView)
    ScienceView mScienceView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_science;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new NewsSciencePresenter(mScienceView);
    }

    @Override
    protected void loadData() {
        ((NewsSciencePresenter)mPresenter).onRefresh();
    }
}
