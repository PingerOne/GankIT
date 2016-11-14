package com.pinger.gankit.ui.adapter;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.adapter
 *  @文件名:   VideoIntroAdapter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/8 14:09
 *  @描述：    TODO
 */

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.pinger.gankit.model.bean.VideoInfo;
import com.pinger.gankit.ui.adapter.holder.VideoIntroHolder;

public class VideoIntroAdapter extends RecyclerArrayAdapter<VideoInfo> {
    public VideoIntroAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoIntroHolder(parent);
    }
}
