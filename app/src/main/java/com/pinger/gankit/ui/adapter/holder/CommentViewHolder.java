package com.pinger.gankit.ui.adapter.holder;/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.adapter.holder
 *  @文件名:   CommentViewHolder
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/8 15:40
 *  @描述：    评论列表Holder
 */

import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.pinger.gankit.R;
import com.pinger.gankit.manager.ImageManager;
import com.pinger.gankit.model.bean.VideoType;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentViewHolder extends BaseViewHolder<VideoType> {
    private CircleImageView mIvAvatar;
    private TextView mTvNick;
    private TextView mTvTime;
    private TextView mTvLike;
    private TextView mTvComment;


    public CommentViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_comment);
        mIvAvatar = $(R.id.avatar);
        mTvNick = $(R.id.tv_nick);
        mTvTime = $(R.id.tv_time);
        mTvLike = $(R.id.tv_like);
        mTvComment = $(R.id.tv_comment);
    }

    @Override
    public void setData(VideoType data) {
        mTvNick.setText(data.phoneNumber);
        mTvTime.setText(data.time);
        mTvLike.setText(data.likeNum);
        mTvComment.setText(data.msg);
        if (!TextUtils.isEmpty(data.userPic))
            ImageManager.load(getContext(), data.userPic, mIvAvatar);
    }
}
