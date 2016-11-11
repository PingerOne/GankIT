package com.pinger.gankit.ui.view;

import android.content.Context;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.pinger.gankit.R;
import com.pinger.gankit.base.RootView;
import com.pinger.gankit.model.bean.VideoType;
import com.pinger.gankit.presenter.contact.VideoCommentContact;
import com.pinger.gankit.ui.adapter.CommentAdapter;
import com.pinger.gankit.utils.ScreenUtil;

import java.util.List;

import butterknife.BindView;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.view
 *  @文件名:   VideoCommentView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/8 14:35
 *  @描述：    TODO
 */

public class VideoCommentView extends RootView<VideoCommentContact.Presenter> implements VideoCommentContact.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    @BindView(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    private CommentAdapter mAdapter;
    private TextView mTvEmpty;

    public VideoCommentView(Context context) {
        this(context, null);
    }

    public VideoCommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.view_comment, this);
    }

    @Override
    protected void initView() {
        // 设置适配器
        mAdapter = new CommentAdapter(mContext);
        mRecyclerView.setAdapterWithProgress(mAdapter);
        mRecyclerView.setErrorView(R.layout.view_error);
        mAdapter.setMore(R.layout.view_more, this);
        mAdapter.setNoMore(R.layout.view_nomore);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 设置条目间隙
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(getContext(), 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        mRecyclerView.addItemDecoration(itemDecoration);
        mTvEmpty = (TextView) mRecyclerView.getEmptyView();
        mTvEmpty.setText("暂无评论");
    }

    @Override
    protected void initEvent() {
        mRecyclerView.setRefreshListener(this);
        // 断开网络
        mAdapter.setError(R.layout.view_error_footer).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.resumeMore();
            }
        });
        // 发生错误的点击事件处理
        mRecyclerView.getErrorView().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.showProgress();
                onRefresh();
            }
        });
    }


    @Override
    public void showContent(List<VideoType> types) {
        mAdapter.clear();
        if (types != null && types.size() < 30) {
            clearFooter();
        }
        mAdapter.addAll(types);
    }

    /**
     * 将布局内容清空
     */
    private void clearFooter() {
        mAdapter.setMore(new View(mContext), this);
        mAdapter.setError(new View(mContext));
        mAdapter.setNoMore(new View(mContext));
    }

    @Override
    public void showMoreContent(List<VideoType> moreTypes) {
        mAdapter.addAll(moreTypes);
    }

    @Override
    public void setPresenter(VideoCommentContact.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void refreshFail(String msg) {
        if (!TextUtils.isEmpty(msg)) showError(msg);
        mRecyclerView.showError();
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
