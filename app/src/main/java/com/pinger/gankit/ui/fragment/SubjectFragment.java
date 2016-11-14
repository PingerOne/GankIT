package com.pinger.gankit.ui.fragment;

/*
 *  @项目名：  GankIT 
 *  @包名：    ui.fragment.content
 *  @文件名:   SubjectFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/1 20:36
 *  @描述：    TODO
 */

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.SubjectPresenter;
import com.pinger.gankit.ui.view.SubjectView;

import butterknife.BindView;

public class SubjectFragment extends BaseFragment {

    @BindView(R.id.subjectView)
    SubjectView mSubjectView;

    @Override
    protected void loadData() {
        // 直接刷新获取数据
        ((SubjectPresenter) mPresenter).onRefresh();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_subject;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        // 创建对应的Presenter
        mPresenter = new SubjectPresenter(mSubjectView);
    }
}