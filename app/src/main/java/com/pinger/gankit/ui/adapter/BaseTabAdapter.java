package com.pinger.gankit.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  GankIT
 *  @包名：    com.pinger.gankit.ui.adapter
 *  @文件名:   BaseTabAdapter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 17:24
 *  @描述：    TODO
 */
public class BaseTabAdapter extends FragmentStatePagerAdapter {

    private final Context mContext;
    private List<TopTab> mTopTabs = new ArrayList<>();

    public BaseTabAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        TopTab topTab = mTopTabs.get(position);
        return Fragment.instantiate(mContext, topTab.clazz.getName(), topTab.bundle);
    }

    @Override
    public int getCount() {
        return mTopTabs.size();
    }


    /**
     * 添加一条标题数据
     */
    public void addTopTab(Class<?> clazz, String title, Bundle bundle) {
        mTopTabs.add(new TopTab(clazz, title, bundle));
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTopTabs != null && mTopTabs.size() > 0) {
            return mTopTabs.get(position).title;
        }
        return super.getPageTitle(position);
    }

    public class TopTab {
        Class<?> clazz;
        String title;
        Bundle bundle;

        public TopTab(Class<?> clazz, String title, Bundle bundle) {
            this.clazz = clazz;
            this.title = title;
            this.bundle = bundle;
        }
    }
}
