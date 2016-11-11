package com.pinger.gankit.ui.fragment;
/*
 *  @项目名：  GankIT 
 *  @包名：    ui.fragment.content
 *  @文件名:   FindFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/1 20:36
 *  @描述：    TODO
 */

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.FindPresenter;
import com.pinger.gankit.ui.view.FindView;

import butterknife.BindView;

public class FindFragment extends BaseFragment {


    @BindView(R.id.findView)
    FindView mFindView;

    @Override
    protected void loadData() {
        // 加载数据的操作交给Presenter
        ((FindPresenter) mPresenter).requestData();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new FindPresenter(mFindView);
    }

}