package com.pinger.gankit.ui.view.gank;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseTabActivity;
import com.pinger.gankit.base.RootView;
import com.pinger.gankit.model.bean.GankBean;
import com.pinger.gankit.presenter.contact.GankContact;
import com.pinger.gankit.presenter.gank.GankBasePresenter;
import com.pinger.gankit.ui.adapter.GankBaseAdapter;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

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

public abstract class GankBaseView extends RootView<GankContact.Presenter> implements GankContact.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    @BindView(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    private GankBaseAdapter mAdapter;

    public GankBaseView(Context context) {
        this(context, null);
    }

    public GankBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.view_gank, this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        // 设置适配器
        mAdapter = new GankBaseAdapter(mContext);
        //mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        // mAdapter.isFirstOnly(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapterWithProgress(mAdapter);
        mRecyclerView.setErrorView(R.layout.view_error);
        // 加载更多
        mAdapter.setMore(R.layout.view_more, this);
        mAdapter.setNoMore(R.layout.view_nomore);
    }

    @Override
    protected void initEvent() {
        // 上拉刷新
        mRecyclerView.setRefreshListener(this);

        mAdapter.setError(R.layout.view_error_footer).setOnClickListener(view -> mAdapter.resumeMore());
        // 错误视图的点击事件处理
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
    public void showContent(List<GankBean> gankBeanList) {
//        mAdapter.clear();
//        if (gankBeanList != null && gankBeanList.size() < GankBasePresenter.PAGENUM) {
//            clearFooter();
//        }
//        mAdapter.addAll(gankBeanList);
        initData(mAdapter, gankBeanList);
    }


    @Override
    public void showMoreContent(List<GankBean> gankBeanMoreList) {
//        mAdapter.addAll(gankBeanMoreList);
        initMoreData(mAdapter, gankBeanMoreList);
    }

    /**
     * 更新数据，让子类实现
     *
     * @param adapter
     * @param data
     * @param isClear
     */
    protected void updateData(GankBaseAdapter adapter, List<GankBean> data, boolean isClear) {
        if (isClear) {
            mAdapter.clear();
            if (data != null && data.size() < GankBasePresenter.PAGENUM) {
                clearFooter();
            }
        }
        adapter.addAll(data);
    }

    protected void clearFooter() {
        mAdapter.setMore(new View(mContext), this);
        mAdapter.setError(new View(mContext));
        mAdapter.setNoMore(new View(mContext));
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

    protected abstract void initData(GankBaseAdapter adapter, List<GankBean> gankBeanList);

    protected abstract void initMoreData(GankBaseAdapter adapter, List<GankBean> gankBeanMoreList);
}
