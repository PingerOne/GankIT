package com.pinger.gankit.ui.adapter.holder;/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.adapter.holder
 *  @文件名:   SubjectViewHolder
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/5 21:01
 *  @描述：    TODO
 */

import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.pinger.gankit.R;
import com.pinger.gankit.manager.ImageManager;
import com.pinger.gankit.model.bean.VideoInfo;

public class SubjectViewHolder extends BaseViewHolder<VideoInfo> {
    ImageView mImgVideo;
    TextView mTvTitle;

    public SubjectViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_subject);

        mImgVideo = $(R.id.img_video);
        mTvTitle = $(R.id.tv_title);
        mImgVideo.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void setData(VideoInfo data) {
        mTvTitle.setText(data.title);

        ViewGroup.LayoutParams params = mImgVideo.getLayoutParams();

        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels / 2;   // 宽度为屏幕宽度一半

        params.height = (int) (width / 1.8);
        mImgVideo.setLayoutParams(params);
        ImageManager.getsInstance().load(getContext(), data.pic, mImgVideo);
    }
}
