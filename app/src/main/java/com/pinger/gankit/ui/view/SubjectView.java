package com.pinger.gankit.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pinger.gankit.R;
import com.pinger.gankit.base.RootView;
import com.pinger.gankit.model.bean.VideoInfo;
import com.pinger.gankit.model.bean.VideoRes;
import com.pinger.gankit.presenter.contact.SubjectContact;
import com.pinger.gankit.ui.activity.MainActivity;
import com.pinger.gankit.ui.adapter.SubjectAdapter;
import com.pinger.gankit.utils.JumpUtil;
import com.pinger.gankit.utils.ScreenUtil;
import com.pinger.gankit.utils.StringUtil;
import com.pinger.gankit.widget.ResideMenu;
import com.pinger.gankit.widget.theme.ColorRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.view
 *  @文件名:   SubjectView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/5 20:08
 *  @描述：    专题的View层
 */

public class SubjectView extends RootView<SubjectContact.Presenter> implements SubjectContact.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.title)
    ColorRelativeLayout mTitle;
    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView mEasyRecyclerView;
    private SubjectAdapter mAdapter;
    private ResideMenu mResideMenu;

    public SubjectView(Context context) {
        this(context, null);
    }

    public SubjectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void showContent(VideoRes videoRes) {
        // 拿数据，分一下类
        if (videoRes != null) {
            mAdapter.clear();
            List<VideoInfo> mData = new ArrayList<>();
            for (int i = 1; i < videoRes.list.size(); i++) {
                if (!TextUtils.isEmpty(videoRes.list.get(i).moreURL) && !TextUtils.isEmpty(videoRes.list.get(i).title)) {
                    VideoInfo videoInfo = videoRes.list.get(i).childList.get(0);
                    videoInfo.title = videoRes.list.get(i).title;
                    videoInfo.moreURL = videoRes.list.get(i).moreURL;
                    mData.add(videoInfo);
                }
            }
            mAdapter.addAll(mData);
        }

    }

    @Override
    public void refreshFail(String msg) {
        if (TextUtils.isEmpty(msg)) {
            showError(msg);
        }
    }

    @Override
    public void setPresenter(SubjectContact.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.view_subject, this);
    }

    @Override
    protected void initView() {
        mTvName.setText(mContext.getString(R.string.video_zhuanti));
        mIvIcon.setImageDrawable(new IconicsDrawable(mContext).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_format_subject).sizeDp(18));
        MainActivity activity = (MainActivity) mContext;
        mAdapter = new SubjectAdapter(null);
        mEasyRecyclerView.setAdapterWithProgress(mAdapter);
        mEasyRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mEasyRecyclerView.setErrorView(R.layout.view_error);
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(getContext(), 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        mEasyRecyclerView.addItemDecoration(itemDecoration);

        mResideMenu = activity.getResideMenu();
    }

    @Override
    protected void initEvent() {
        // 刷新监听
        mEasyRecyclerView.setRefreshListener(this);

        // 错误视图监听
        mEasyRecyclerView.getErrorView().setOnClickListener(view -> {
            mEasyRecyclerView.showProgress();
            onRefresh();
        });

        // 条目监听
        mAdapter.setOnItemClickListener(position -> {
            // 跳转到视频列表
            JumpUtil.jump2VideoListActivity(mContext, StringUtil.getCatalogId(mAdapter.getItem(position).moreURL),
                    mAdapter.getItem(position).title);
        });

        mIvIcon.setOnClickListener(view -> {
            if (mResideMenu.isOpened()) {
                mResideMenu.closeMenu();
            } else {
                mResideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
    }

    @Override
    public void onRefresh() {
        // 调用没Presenter层刷新数据
        mPresenter.onRefresh();
    }
}
