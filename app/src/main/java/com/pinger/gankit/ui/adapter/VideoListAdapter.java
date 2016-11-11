package com.pinger.gankit.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.pinger.gankit.model.bean.VideoType;
import com.pinger.gankit.ui.adapter.holder.VideoListHolder;

/*
 *  @项目名：  GankIT
 *  @包名：    com.pinger.gankit.ui.adapter
 *  @文件名:   VideoListAdapter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/8 17:48
 *  @描述：    视频列表Adapter
 */
public class VideoListAdapter extends RecyclerArrayAdapter<VideoType>{
    public VideoListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoListHolder(parent);
    }
}
