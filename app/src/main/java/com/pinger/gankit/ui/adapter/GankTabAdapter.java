package com.pinger.gankit.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pinger.gankit.manager.FragmentFactory;

/*
 *  @项目名：  GankIT
 *  @包名：    com.pinger.gankit.ui.adapter
 *  @文件名:   GankTabAdapter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/9 21:32
 *  @描述：    TODO
 */
public class GankTabAdapter extends FragmentStatePagerAdapter {
    private String[] mTitles;

    public GankTabAdapter(FragmentManager fragmentManager, String[] titles) {
        super(fragmentManager);
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getInstance().getFragment(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
