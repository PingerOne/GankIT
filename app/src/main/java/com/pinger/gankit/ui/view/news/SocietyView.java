package com.pinger.gankit.ui.view.news;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.view.news
 *  @文件名:   SocietyView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/14 13:28
 *  @描述：    TODO
 */

import android.content.Context;
import android.util.AttributeSet;

import com.pinger.gankit.model.bean.NewsBean;
import com.pinger.gankit.ui.adapter.NewsBaseAdapter;

import java.util.List;

public class SocietyView extends NewsBaseView {
    public SocietyView(Context context) {
        this(context, null);
    }

    public SocietyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initData(NewsBaseAdapter adapter, List<NewsBean.ContentList> newsList) {
        updateData(adapter, newsList, true);
    }

    @Override
    protected void initMoreData(NewsBaseAdapter adapter, List<NewsBean.ContentList> newsMoreList) {
        updateData(adapter, newsMoreList, false);
    }
}
