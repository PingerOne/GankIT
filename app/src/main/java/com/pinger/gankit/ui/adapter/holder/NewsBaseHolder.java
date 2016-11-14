package com.pinger.gankit.ui.adapter.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.pinger.gankit.R;
import com.pinger.gankit.model.bean.NewsBean;
import com.pinger.gankit.utils.BeanUtil;
import com.pinger.gankit.utils.JumpUtil;

import java.util.List;

/*
 *  @项目名：  GankIT
 *  @包名：    com.pinger.gankit.ui.adapter.holder
 *  @文件名:   NewsBaseHolder
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/13 20:10
 *  @描述：    TODO
 */
public class NewsBaseHolder extends BaseViewHolder<NewsBean.ContentList> {
    private TextView mTitle, mDesc, mPubDate, mSource;
    private NineGridView mNineGridView;
    private View convertView;

    public NewsBaseHolder(ViewGroup parent) {
        super(parent, R.layout.item_news_base);

        mTitle = $(R.id.title);
        mDesc = $(R.id.desc);
        mPubDate = $(R.id.pubDate);
        mSource = $(R.id.source);
        mNineGridView = $(R.id.nineGrid);
        convertView = $(R.id.convertView);
    }


    @Override
    public void setData(NewsBean.ContentList data) {
        mTitle.setText(data.title);
        mPubDate.setText(data.pubDate);
        mSource.setText(data.source);
        mDesc.setText(data.desc);

        // 条目点击事件
        convertView.setOnClickListener(view -> JumpUtil.jump2WebActivity(getContext(), data.title, data.link));

        // NineGridView默认是使用Picasso加载图片的，这里使用Glide，所以需要在App在配置加载方式
        // 九宫格图片
        List<ImageInfo> imageInfos = BeanUtil.ImageList2ImageInfoList(data.imageurls);
        mNineGridView.setAdapter(new NineGridViewClickAdapter(getContext(), imageInfos));

        // 只有一张图片时
        if (data.imageurls != null && data.imageurls.size() == 1) {
            // 适配单张图片
            mNineGridView.setSingleImageRatio(data.imageurls.get(0).width * 1.0f / data.imageurls.get(0).height);
        }
    }
}

