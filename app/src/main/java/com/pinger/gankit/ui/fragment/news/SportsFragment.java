package com.pinger.gankit.ui.fragment.news;

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.news.NewsSportsPresenter;
import com.pinger.gankit.ui.view.news.SportsView;

import butterknife.BindView;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.fragment.news
 *  @文件名:   SportsFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/13 18:52
 *  @描述：    TODO
 */

public class SportsFragment extends BaseFragment {
    @BindView(R.id.sportsView)
    SportsView mSportsView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sports;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new NewsSportsPresenter(mSportsView);
    }

    @Override
    protected void loadData() {
        ((NewsSportsPresenter) mPresenter).onRefresh();
    }
}
