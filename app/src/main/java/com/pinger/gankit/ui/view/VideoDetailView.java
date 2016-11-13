package com.pinger.gankit.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pinger.gankit.R;
import com.pinger.gankit.base.RootView;
import com.pinger.gankit.manager.ImageManager;
import com.pinger.gankit.model.bean.VideoRes;
import com.pinger.gankit.presenter.contact.VideoDetailContact;
import com.pinger.gankit.ui.activity.VideoDetailActivity;
import com.pinger.gankit.ui.fragment.VideoCommentFragment;
import com.pinger.gankit.ui.fragment.VideoIntroFragment;
import com.pinger.gankit.widget.ProgressView;
import com.pinger.gankit.widget.SwipeViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.view
 *  @文件名:   VideoDetailView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/6 23:27
 *  @描述：    视频详情页的View层
 */

public class VideoDetailView extends RootView<VideoDetailContact.Presenter> implements VideoDetailContact.View {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.videoPlayer)
    JCVideoPlayerStandard mVideoPlayer;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    SwipeViewPager mViewPager;
    @BindView(R.id.loadingView)
    ProgressView mLoadingView;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.tv_name)
    TextView mTvName;
    private List<Fragment> mFragments;
    private String[] mTitles;

    public VideoDetailView(Context context) {
        this(context, null);
    }

    public VideoDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.view_video_detail, this);
    }

    @Override
    protected void initView() {
        mIvIcon.setImageDrawable(new IconicsDrawable(mContext).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_arrow_back).sizeDp(20));
        mIvShare.setImageDrawable(new IconicsDrawable(mContext).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_share).sizeDp(20));

        initData();
        // 设置ViewPager和TabLayout
        mViewPager.setAdapter(new VideoDetailAdapter(((VideoDetailActivity) mContext).getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager, true);

        // 设置视频播放器
        mVideoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mVideoPlayer.backButton.setVisibility(View.GONE);
        mVideoPlayer.titleTextView.setVisibility(View.GONE);
        mVideoPlayer.tinyBackImageView.setVisibility(View.GONE);
    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new VideoIntroFragment());
        mFragments.add(new VideoCommentFragment());

        mTitles = new String[]{mContext.getResources().getString(R.string.intro), mContext.getResources().getString(R.string.comment)};
    }


    @Override
    protected void initEvent() {
        mIvIcon.setOnClickListener(view -> ((VideoDetailActivity) mContext).finish());
    }


    @Override
    public void showContent(VideoRes videoRes) {
        mTvName.setText(videoRes.title);

        // 根据资源播放视频
        if (!TextUtils.isEmpty(videoRes.pic))
            // 显示最上层图片
            ImageManager.load(mContext, videoRes.pic, mVideoPlayer.thumbImageView);
        if (!TextUtils.isEmpty(videoRes.getVideoUrl())) {
            // 播放视频
            mVideoPlayer.setUp(videoRes.getVideoUrl()
                    , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, videoRes.title);

            // 点击事件
            mVideoPlayer.onClick(mVideoPlayer.thumbImageView);
        }
    }

    @Override
    public void hideLoading() {
        mLoadingView.setVisibility(GONE);
    }

    @Override
    public void collected() {

    }

    @Override
    public void disCollect() {

    }

    @Override
    public void setPresenter(VideoDetailContact.Presenter presenter) {
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

    private class VideoDetailAdapter extends FragmentPagerAdapter {

        public VideoDetailAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
