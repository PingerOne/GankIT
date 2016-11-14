package com.pinger.gankit.ui.fragment.news;

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.news.NewsMilitaryPresenter;
import com.pinger.gankit.ui.view.news.MilitaryView;

import butterknife.BindView;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.fragment.news
 *  @文件名:   MilitaryFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/13 18:51
 *  @描述：    TODO
 */

public class MilitaryFragment extends BaseFragment {
    @BindView(R.id.militaryView)
    MilitaryView mMilitaryView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_military;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new NewsMilitaryPresenter(mMilitaryView);
    }

    @Override
    protected void loadData() {
        ((NewsMilitaryPresenter) mPresenter).onRefresh();
    }

}
