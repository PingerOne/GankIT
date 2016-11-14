package com.pinger.gankit.ui.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pinger.gankit.R;
import com.pinger.gankit.base.RootView;
import com.pinger.gankit.model.bean.GankBean;
import com.pinger.gankit.presenter.FuliPresenter;
import com.pinger.gankit.presenter.contact.FuliContact;
import com.pinger.gankit.ui.activity.PhotoActivity;
import com.pinger.gankit.ui.adapter.FuliAdapter;

import java.util.List;

import butterknife.BindView;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.view
 *  @文件名:   FuliView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/12 0:05
 *  @描述：    福利View层
 */

public class FuliView extends RootView<FuliContact.Presenter> implements FuliContact.View, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    private FuliAdapter mAdapter;

    public FuliView(Context context) {
        this(context, null);
    }

    public FuliView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.view_fuli, this);
    }

    @Override
    protected void initView() {
        mTvName.setText(mContext.getString(R.string.fuli));
        mIvIcon.setImageDrawable(new IconicsDrawable(mContext).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_face).sizeDp(20));

        // 设置适配器
        mAdapter = new FuliAdapter(mContext);
        mRecyclerView.setAdapterWithProgress(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setErrorView(R.layout.view_error);
        mAdapter.setMore(R.layout.view_more, this);
        mAdapter.setNoMore(R.layout.view_nomore);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected void initEvent() {
        mRecyclerView.setRefreshListener(this);
        mAdapter.setError(R.layout.view_error_footer).setOnClickListener(view -> mAdapter.resumeMore());
        mRecyclerView.getErrorView().setOnClickListener(view -> {
            mRecyclerView.showProgress();
            onRefresh();
        });

        // 设置条目点击事件
        mAdapter.setOnItemClickListener(position -> {
            GankBean gankBean = mAdapter.getAllData().get(position);
            Intent intent = new Intent(mContext, PhotoActivity.class);
            intent.putExtra(PhotoActivity.IMAGE_TITLE, gankBean.getDesc());
            intent.putExtra(PhotoActivity.IMAGE_URL, gankBean.getUrl());
            mContext.startActivity(intent);
        });
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void showContent(List<GankBean> gankBeanList) {
        mAdapter.clear();
        if (gankBeanList != null && gankBeanList.size() < FuliPresenter.PAGENUM) {
            clearFooter();
        }
        mAdapter.addAll(gankBeanList);
    }

    @Override
    public void showMoreContent(List<GankBean> gankBeanMoreList) {
        mAdapter.addAll(gankBeanMoreList);
    }

    /**
     * 清空布局
     */
    private void clearFooter() {
        mAdapter.setMore(new View(mContext), this);
        mAdapter.setError(new View(mContext));
        mAdapter.setNoMore(new View(mContext));
    }

    @Override
    public void setPresenter(FuliContact.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {
        Snackbar.make(mRecyclerView, msg, Snackbar.LENGTH_SHORT).show();
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
}
