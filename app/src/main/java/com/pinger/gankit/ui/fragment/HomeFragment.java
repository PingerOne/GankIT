package com.pinger.gankit.ui.fragment;

/*
 *  @项目名：  GankIT 
 *  @包名：    ui.fragment.content
 *  @文件名:   HomeFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/1 20:36
 *  @描述：    主页对应的Fragment
 */

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.HomePresenter;
import com.pinger.gankit.ui.view.HomeView;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.homeView)
    HomeView mHomeView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        // 将View传递给Presenter
        mPresenter = new HomePresenter(mHomeView);
    }

    @Override
    protected void loadData() {
        // 使用Presenter加载数据,调用刷新方法
        ((HomePresenter) mPresenter).onRefresh();
    }
}