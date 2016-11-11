package com.pinger.gankit.ui.adapter;/*
 *  @项目名：  GankIT 
 *  @包名：    ui.adapter
 *  @文件名:   HomedAdapter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 13:50
 *  @描述：    TODO
 */

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.pinger.gankit.ui.adapter.holder.HomeViewHolder;
import com.pinger.gankit.model.bean.VideoInfo;

public class HomedAdapter extends RecyclerArrayAdapter<VideoInfo> {

    public HomedAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeViewHolder(parent);
    }

}
