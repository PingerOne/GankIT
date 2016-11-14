package com.pinger.gankit.ui.adapter.holder;/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.adapter.holder
 *  @文件名:   VideoIntroHolder
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/8 14:11
 *  @描述：    视频简介推荐条目Holder
 */

import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.pinger.gankit.R;
import com.pinger.gankit.manager.ImageManager;
import com.pinger.gankit.model.bean.VideoInfo;

public class VideoIntroHolder extends BaseViewHolder<VideoInfo> {
    private ImageView mImgPicture;
    private TextView mTvTitle;

    public VideoIntroHolder(ViewGroup parent) {
        super(parent, R.layout.item_intro);
        mImgPicture = $(R.id.img_video);
        mTvTitle = $(R.id.tv_title);
        mImgPicture.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void setData(VideoInfo data) {
        mTvTitle.setText(data.title);
        ViewGroup.LayoutParams params = mImgPicture.getLayoutParams();

        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels / 3;

        params.height = (int) (width * 1.2);
        mImgPicture.setLayoutParams(params);
        ImageManager.getsInstance().load(getContext(), data.pic, mImgPicture);
    }
}
