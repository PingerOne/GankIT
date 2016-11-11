package com.pinger.gankit.ui.adapter.holder;
/*
 *  @项目名：  GankIT 
 *  @包名：    ui.adapter.holder
 *  @文件名:   HomeViewHolder
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 13:52
 *  @描述：    影片条目布局
 */

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.pinger.gankit.R;
import com.pinger.gankit.manager.ImageManager;
import com.pinger.gankit.model.bean.VideoInfo;

public class HomeViewHolder extends BaseViewHolder<VideoInfo> {
    ImageView imgPicture;
    TextView tv_title;

    public HomeViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_video);
        imgPicture = $(R.id.img_video);
        tv_title = $(R.id.tv_title);
        imgPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public void setData(VideoInfo data) {
        tv_title.setText(data.title);
        ImageManager.load(getContext(), data.pic, imgPicture);
    }
}
