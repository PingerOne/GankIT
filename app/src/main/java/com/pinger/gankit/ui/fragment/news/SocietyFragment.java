package com.pinger.gankit.ui.fragment.news;

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.news.NewsSocietyPresenter;
import com.pinger.gankit.ui.view.news.SocietyView;

import butterknife.BindView;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.fragment.news
 *  @文件名:   SocietyFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/13 18:44
 *  @描述：    TODO
 */

public class SocietyFragment extends BaseFragment {

    @BindView(R.id.societyView)
    SocietyView mSocietyView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_news_society;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new NewsSocietyPresenter(mSocietyView);
    }

    @Override
    protected void loadData() {
        ((NewsSocietyPresenter) mPresenter).onRefresh();
    }
}
