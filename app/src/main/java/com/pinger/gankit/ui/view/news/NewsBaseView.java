package com.pinger.gankit.ui.view.news;

import android.content.Context;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.pinger.gankit.R;
import com.pinger.gankit.base.RootView;
import com.pinger.gankit.model.bean.NewsBean;
import com.pinger.gankit.presenter.contact.NewsContact;
import com.pinger.gankit.ui.adapter.NewsBaseAdapter;

import java.util.List;

import butterknife.BindView;


/*
 *  @项目名：  GankIT
 *  @包名：    com.pinger.gankit.ui.view.news
 *  @文件名:   NewsView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/13 19:07
 *  @描述：    TODO
 */

public abstract class NewsBaseView extends RootView<NewsContact.Presenter> implements NewsContact.View, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    private NewsBaseAdapter mAdapter;

    public NewsBaseView(Context context) {
        this(context, null);
    }

    public NewsBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.view_news_base, this);
    }

    @Override
    protected void initView() {
        mAdapter = new NewsBaseAdapter(mContext);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapterWithProgress(mAdapter);
        mRecyclerView.setErrorView(R.layout.view_error);

        mAdapter.setMore(R.layout.view_more, this);
        mAdapter.setNoMore(R.layout.view_nomore);
    }

    @Override
    protected void initEvent() {
        mRecyclerView.setRefreshListener(this);

        mAdapter.setError(R.layout.view_error_footer).setOnClickListener(view -> mAdapter.resumeMore());
        mRecyclerView.getErrorView().setOnClickListener(view -> {
            mRecyclerView.showProgress();
            onRefresh();
        });
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void showContent(List<NewsBean.ContentList> newsList) {
        initData(mAdapter, newsList);
    }

    @Override
    public void showMoreContent(List<NewsBean.ContentList> newsMoreList) {
        initMoreData(mAdapter, newsMoreList);
    }


    /**
     * 更新数据，让子类实现
     *
     * @param adapter
     * @param newsList
     * @param isClear
     */
    protected void updateData(NewsBaseAdapter adapter, List<NewsBean.ContentList> newsList, boolean isClear) {
        if (isClear) {
            mAdapter.clear();
            if (newsList != null && newsList.size() < 20) {
                clearFooter();
            }
        }
        adapter.addAll(newsList);
    }

    protected void clearFooter() {
        mAdapter.setMore(new View(mContext), this);
        mAdapter.setError(new View(mContext));
        mAdapter.setNoMore(new View(mContext));
    }


    @Override
    public void setPresenter(NewsContact.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshFailed(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            showError(msg);
        }
        mRecyclerView.showError();
    }

    @Override
    public void loadMoreFailed(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            showError(msg);
        }
        mAdapter.pauseMore();
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    public void onLoadMore() {
        mPresenter.loadMore();
    }


    protected abstract void initData(NewsBaseAdapter adapter, List<NewsBean.ContentList> newsList);

    protected abstract void initMoreData(NewsBaseAdapter adapter, List<NewsBean.ContentList> newsMoreList);


}
