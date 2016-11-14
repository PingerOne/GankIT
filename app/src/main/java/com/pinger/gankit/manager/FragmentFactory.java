package com.pinger.gankit.manager;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.manager
 *  @文件名:   FragmentFactory
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/9 23:01
 *  @描述：    Fragment工厂，用于生产Fragment
 */

import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.ui.fragment.gank.AllFragment;
import com.pinger.gankit.ui.fragment.gank.AndroidFragment;
import com.pinger.gankit.ui.fragment.gank.AppFragment;
import com.pinger.gankit.ui.fragment.gank.FuliFragment;
import com.pinger.gankit.ui.fragment.gank.IOSFragment;
import com.pinger.gankit.ui.fragment.gank.ResourceFragment;
import com.pinger.gankit.ui.fragment.gank.TuiJianFragment;
import com.pinger.gankit.ui.fragment.gank.WebFragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentFactory {

    // 缓存Fragment
    private Map<Integer, BaseFragment> mCaches = new HashMap<>();

    private static FragmentFactory sInstance;

    private FragmentFactory() {
    }

    public synchronized static FragmentFactory getInstance() {
        if (sInstance == null) {
            sInstance = new FragmentFactory();
        }
        return sInstance;
    }

    public BaseFragment getFragment(int position) {
        // 做一个集合记录下来所有fragment，下一次直接取出，这样保证了唯一性，就是内存缓存
        BaseFragment fragment;
        if (mCaches.containsKey(position)) {
            // 上一次已保存的情况，取出来即可
            fragment = mCaches.get(position);
            return fragment;
        }
        switch (position) {
            case 0:
                fragment = new AllFragment();
                break;
            case 1:
                fragment = new AndroidFragment();
                break;
            case 2:
                fragment = new IOSFragment();
                break;
            case 3:
                fragment = new AppFragment();
                break;
            case 4:
                fragment = new ResourceFragment();
                break;
            case 5:
                fragment = new WebFragment();
                break;
            case 6:
                fragment = new TuiJianFragment();
                break;
            case 7:
                fragment = new FuliFragment();
                break;
            default:
                fragment = new AllFragment();
        }
        mCaches.put(position, fragment);
        return fragment;
    }
}
