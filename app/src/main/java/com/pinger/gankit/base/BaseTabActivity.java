package com.pinger.gankit.base;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.pinger.gankit.R;
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

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    SwipeViewPager mViewPager;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
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
        setupWithTopTab(mAdapter);
    }

    /**
     * 让子类去填充标题
     *
     * @param tvName
     * @param ivIcon
     */
    protected abstract void initToolBar(TextView tvName, ImageView ivIcon);

    protected abstract void setupWithTopTab(BaseTabAdapter adapter);
}
