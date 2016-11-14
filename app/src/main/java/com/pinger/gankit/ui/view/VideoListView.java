package com.pinger.gankit.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pinger.gankit.R;
import com.pinger.gankit.base.RootView;
import com.pinger.gankit.model.bean.VideoInfo;
import com.pinger.gankit.model.bean.VideoType;
import com.pinger.gankit.presenter.contact.VideoListContact;
import com.pinger.gankit.ui.activity.VideoListActivity;
import com.pinger.gankit.ui.adapter.VideoListAdapter;
import com.pinger.gankit.utils.BeanUtil;
import com.pinger.gankit.utils.JumpUtil;
import com.pinger.gankit.utils.ScreenUtil;

import java.util.List;

import butterknife.BindView;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.view
 *  @文件名:   VideoListView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/8 16:42
 *  @描述：    视频列表的View层
 */

public class VideoListView extends RootView<VideoListContact.Presenter> implements VideoListContact.View, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;
    private VideoListActivity mActivity;
    private VideoListAdapter mAdapter;
    private VideoInfo videoInfo;
    private static final int PAGE_SIZE = 30;

    public VideoListView(Context context) {
        this(context, null);
    }

    public VideoListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.view_video_list, this);
    }

    @Override
    protected void initView() {
        mActivity = (VideoListActivity) mContext;
        mIvIcon.setImageDrawable(new IconicsDrawable(mContext).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_arrow_left).sizeDp(16));

        // 设置适配器
        mAdapter = new VideoListAdapter(mContext);
        mRecyclerView.setAdapterWithProgress(mAdapter);
        mRecyclerView.setErrorView(R.layout.view_error);
        // 加载更多
        mAdapter.setMore(R.layout.view_more_white, this);
        mAdapter.setNoMore(R.layout.view_nomore_white);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        gridLayoutManager.setSpanSizeLookup(mAdapter.obtainGridSpanSizeLookUp(3));
        mRecyclerView.setLayoutManager(gridLayoutManager);
        // 条目间隙
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(mContext, 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    protected void initEvent() {
        mFab.setOnClickListener(view -> mRecyclerView.scrollToPosition(0));

        mIvIcon.setOnClickListener(view -> mActivity.finish());
        mRecyclerView.setRefreshListener(this);
        mAdapter.setOnItemClickListener(position -> {
            // 将VideoType转换成VideoInfo
            videoInfo = BeanUtil.VideoType2VideoInfo(mAdapter.getItem(position), videoInfo);
            JumpUtil.jump2VideoDetailActivity(getContext(), videoInfo);
        });

        // 更多的错误处理
        mAdapter.setError(R.layout.view_error_footer).setOnClickListener(v -> mAdapter.resumeMore());

        // 错误视图的点击事件处理
        mRecyclerView.getErrorView().setOnClickListener(v -> {
            mRecyclerView.showProgress();
            onRefresh();
        });
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void showTitle(String title) {
        mTvName.setText(title);
    }

    @Override
    public void refreshFail(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            showError(msg);
        }
        mRecyclerView.showError();
    }

    @Override
    public void loadMoreFail(String msg) {
        if (!TextUtils.isEmpty(msg))
            showError(msg);
        mAdapter.pauseMore();
    }

    @Override
    public void showContent(List<VideoType> typeList) {
        mAdapter.clear();
        if (typeList != null && typeList.size() < PAGE_SIZE) {
            clearFooter();
        }
        mAdapter.addAll(typeList);
    }

    @Override
    public void showMoreContent(List<VideoType> moreTypeList) {
        mAdapter.addAll(moreTypeList);
    }

    /**
     * 重置布局
     */
    private void clearFooter() {
        mAdapter.setMore(new View(mContext), this);
        mAdapter.setError(new View(mContext));
        mAdapter.setNoMore(new View(mContext));
    }

    @Override
    public void setPresenter(VideoListContact.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore() {
        mPresenter.loadMore();
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }
}
