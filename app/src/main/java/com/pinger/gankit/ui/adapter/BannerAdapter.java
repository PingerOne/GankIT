package com.pinger.gankit.ui.adapter;/*
 *  @项目名：  GankIT 
 *  @包名：    ui.adapter
 *  @文件名:   BannerAdapter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 15:13
 *  @描述：    轮播图适配器
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.pinger.gankit.R;
import com.pinger.gankit.manager.ImageManager;
import com.pinger.gankit.model.bean.VideoInfo;
import com.pinger.gankit.utils.JumpUtil;

import java.util.List;

public class BannerAdapter extends StaticPagerAdapter {
    private Context mContext;
    private List<VideoInfo> list;

    public BannerAdapter(Context context, List<VideoInfo> list) {
        this.mContext = context;
        this.list = list;
        removeEmpty(this.list);
    }

    private void removeEmpty(List<VideoInfo> list) {
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).loadType.equals("video")) {
                list.remove(i);
                i--;
            }
        }
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setBackgroundResource(R.mipmap.default_320);
        //加载图片
        ImageManager.getsInstance().load(mContext, list.get(position).pic, imageView);
        //点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jump2VideoDetailActivity(mContext, list.get(position));
            }
        });
        return imageView;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
