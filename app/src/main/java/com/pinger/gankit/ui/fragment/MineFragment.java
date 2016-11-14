package com.pinger.gankit.ui.fragment;

/*
 *  @项目名：  GankIT 
 *  @包名：    ui.fragment.content
 *  @文件名:   MineFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/1 20:36
 *  @描述：    TODO
 */

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.MinePresenter;
import com.pinger.gankit.ui.view.MineView;

import butterknife.BindView;

public class MineFragment extends BaseFragment {


    @BindView(R.id.mineView)
    MineView mMineView;

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new MinePresenter(mMineView);
    }
}
