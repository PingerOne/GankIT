package com.pinger.gankit.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.pinger.gankit.model.bean.GankBean;
import com.pinger.gankit.ui.adapter.holder.FuliHolder;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.adapter
 *  @文件名:   FuliAdapter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/12 22:43
 *  @描述：    TODO
 */
public class FuliAdapter extends RecyclerArrayAdapter<GankBean> {
    public FuliAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new FuliHolder(parent);
    }
}
