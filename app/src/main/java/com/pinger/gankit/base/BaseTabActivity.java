package com.pinger.gankit.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.pinger.gankit.R;
import com.pinger.gankit.ui.activity.WebActivity;
import com.pinger.gankit.ui.adapter.BaseTabAdapter;
import com.pinger.gankit.widget.SwipeViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *  @项目名：  GankIT
 *  @包名：    com.pinger.gankit.base
 *  @文件名:   BaseTabActivity
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 17:17
 *  @描述：    TODO
 */

public abstract class BaseTabActivity extends SwipeBackActivity {

    @BindView(R.id.iv_icon) @Nullable ImageView mIvIcon;
    @BindView(R.id.tv_name) @Nullable TextView mTvName;
    @BindView(R.id.tabLayout) @Nullable TabLayout mTabLayout;
    @BindView(R.id.viewPager) @Nullable SwipeViewPager mViewPager;
    @BindView(R.id.fab) @Nullable FloatingActionButton mFab;

    private BaseTabAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_tab);
        unbinder = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        initToolBar(mTvName, mIvIcon);

        // 标题数据
        mAdapter = new BaseTabAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        // 设置数据
        int count = setupWithTopTab(mAdapter);
        // 优化加载
        mViewPager.setOffscreenPageLimit(count);
        mAdapter.notifyDataSetChanged();

        // Fab点击事件
        mFab.setOnClickListener(view -> {
            // 加载网页
            Intent intent = new Intent(BaseTabActivity.this, WebActivity.class);
            intent.putExtra(WebActivity.WEB_TITLE, getString(R.string.share_github_title));
            intent.putExtra(WebActivity.WEB_URL, getString(R.string.github));
            startActivity(intent);
        });
    }

    /**
     * 让子类去填充标题
     *
     * @param tvName
     * @param ivIcon
     */
    protected abstract void initToolBar(TextView tvName, ImageView ivIcon);

    protected abstract int setupWithTopTab(BaseTabAdapter adapter);
}
