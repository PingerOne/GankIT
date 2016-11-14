package com.pinger.gankit.ui.adapter;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.adapter
 *  @文件名:   CommentAdapter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/8 15:39
 *  @描述：    TODO
 */

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.pinger.gankit.model.bean.VideoType;
import com.pinger.gankit.ui.adapter.holder.CommentViewHolder;

public class CommentAdapter extends RecyclerArrayAdapter<VideoType> {
    public CommentAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(parent);
    }
}
