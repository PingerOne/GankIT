package com.pinger.gankit.ui.adapter.holder;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.pinger.gankit.R;
import com.pinger.gankit.manager.ImageManager;
import com.pinger.gankit.model.bean.GankBean;

/*
 *  @项目名：  GankIT
 *  @包名：    com.pinger.gankit.ui.adapter.holder
 *  @文件名:   FuliHolder
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/12 22:44
 *  @描述：    TODO
 */
public class FuliHolder extends BaseViewHolder<GankBean> {

    private ImageView mPicture;

    public FuliHolder(ViewGroup parent) {
        super(parent, R.layout.item_pictrue);
        mPicture = $(R.id.picture);
    }

    @Override
    public void setData(GankBean data) {
        ViewGroup.LayoutParams params = mPicture.getLayoutParams();
        params.height = data.getHeight();
        mPicture.setLayoutParams(params);
        ImageManager.getsInstance().load(getContext(), data.getUrl(), mPicture);
    }
}
