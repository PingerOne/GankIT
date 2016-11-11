package com.pinger.gankit.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/*
 *  @项目名：  GankIT 
 *  @包名：    ui.adapter
 *  @文件名:   ContentPagerAdapter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/1 20:27
 *  @描述：    主页填充Fragment的适配器，不需要去做缓存，FragmentPagerAdapter内部做好了缓存处理
 */

public class ContentPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private final List<Fragment> mData;

    public ContentPagerAdapter(Context context, FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }
}
