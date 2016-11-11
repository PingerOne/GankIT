package com.pinger.gankit.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pinger.gankit.R;
import com.pinger.gankit.manager.ImageManager;
import com.pinger.gankit.model.bean.VideoInfo;
import com.pinger.gankit.model.bean.VideoType;
import com.pinger.gankit.utils.JumpUtil;
import com.pinger.gankit.widget.theme.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.adapter
 *  @文件名:   SwipeDeckAdapter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/5 23:49
 *  @描述：    卡片的适配器
 */

public class SwipeDeckAdapter extends BaseAdapter {

    private List<VideoType> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private VideoInfo mVideoInfo;

    public SwipeDeckAdapter(List<VideoType> data, Context context) {
        this.mData = data;
        this.mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_card, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageManager.load(mContext, mData.get(position).pic, holder.offerImage);
        String intro = "\t\t\t" + mData.get(position).description;
        holder.tvIntroduction.setText(intro);
        holder.tv_title.setText(mData.get(position).title);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchData(mData.get(position));
                JumpUtil.jump2VideoDetailActivity(mContext, mVideoInfo);
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.offer_image)
        RoundedImageView offerImage;
        @BindView(R.id.tv_introduction)
        TextView tvIntroduction;
        @BindView(R.id.card_view)
        CardView cardView;
        @BindView(R.id.tv_title)
        TextView tv_title;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private void switchData(VideoType videoType) {
        if (mVideoInfo == null)
            mVideoInfo = new VideoInfo();
        mVideoInfo.title = videoType.title;
        mVideoInfo.dataId = videoType.dataId;
        mVideoInfo.pic = videoType.pic;
        mVideoInfo.airTime = videoType.airTime;
        mVideoInfo.score = videoType.score;
    }

}
