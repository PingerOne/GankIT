package com.pinger.gankit.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.pinger.gankit.model.bean.NewsBean;
import com.pinger.gankit.ui.adapter.holder.NewsBaseHolder;

/*
 *  @项目名：  GankIT
 *  @包名：    com.pinger.gankit.ui.adapter
 *  @文件名:   NewsBaseAdapter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/13 20:08
 *  @描述：    TODO
 */
public class NewsBaseAdapter extends RecyclerArrayAdapter<NewsBean.ContentList> {
    public NewsBaseAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsBaseHolder(parent);
    }
}
