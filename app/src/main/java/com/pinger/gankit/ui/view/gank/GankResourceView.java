package com.pinger.gankit.ui.view.gank;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.view.gank
 *  @文件名:   GankResourceView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 14:29
 *  @描述：    TODO
 */

import android.content.Context;
import android.util.AttributeSet;

import com.pinger.gankit.model.bean.GankBean;
import com.pinger.gankit.ui.adapter.GankBaseAdapter;

import java.util.List;

public class GankResourceView extends GankBaseView {
    public GankResourceView(Context context) {
        this(context, null);
    }

    public GankResourceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initData(GankBaseAdapter adapter, List<GankBean> gankBeanList) {
        updateData(adapter, gankBeanList, true);
    }

    @Override
    protected void initMoreData(GankBaseAdapter adapter, List<GankBean> gankBeanMoreList) {
        updateData(adapter, gankBeanMoreList, false);
    }
}
