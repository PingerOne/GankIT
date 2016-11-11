package com.pinger.gankit.ui.view.gank;

import android.content.Context;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.pinger.gankit.R;
import com.pinger.gankit.base.RootView;
import com.pinger.gankit.model.bean.GankBean;
import com.pinger.gankit.presenter.contact.GankContact;
import com.pinger.gankit.ui.adapter.GankAdapter;

import java.util.List;

import butterknife.BindView;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.view
 *  @文件名:   GankView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 10:17
 *  @描述：    TODO
 */

public abstract class GankBaseView extends RootView<GankContact.Presenter> implements GankContact.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    private GankAdapter mAdapter;

    public GankBaseView(Context context) {
        this(context, null);
    }

    public GankBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.view_gank, this);
    }

    @Override
    protected void initView() {
        // 设置适配器
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new GankAdapter(null);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mAdapter.isFirstOnly(true);
        mRecyclerView.setAdapterWithProgress(mAdapter);
        mRecyclerView.setErrorView(R.layout.view_error);
    }

    @Override
    protected void initEvent() {
        // 错误视图的点击事件处理
        mRecyclerView.getErrorView().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.showProgress();
                onRefresh();
            }
        });

        // 上拉刷新，下拉加载更多监听
        mRecyclerView.setRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this);
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void showContent(List<GankBean> gankBeanList) {
        initData(mAdapter, gankBeanList);
    }


    @Override
    public void showMoreContent(List<GankBean> gankBeanMoreList) {
        initMoreData(mAdapter, gankBeanMoreList);
    }

    @Override
    public void setPresenter(GankContact.Presenter presenter) {
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
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    public void onLoadMoreRequested() {
        if (mPresenter != null) {
            mPresenter.loadMore();
        }
    }

    protected abstract void initData(GankAdapter adapter, List<GankBean> gankBeanList);

    protected abstract void initMoreData(GankAdapter adapter, List<GankBean> gankBeanMoreList);

}
