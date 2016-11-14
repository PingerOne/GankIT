package com.pinger.gankit.ui.fragment.news;

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.news.NewsTopPresenter;
import com.pinger.gankit.ui.view.news.NewsTopView;

import butterknife.BindView;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.fragment.news
 *  @文件名:   NewsTopFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 17:41
 *  @描述：    TODO
 */

public class NewsTopFragment extends BaseFragment {
    @BindView(R.id.newsTopView)
    NewsTopView mNewsTopView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_news_top;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new NewsTopPresenter(mNewsTopView);
    }

    @Override
    protected void loadData() {
        ((NewsTopPresenter) mPresenter).onRefresh();
    }
}
