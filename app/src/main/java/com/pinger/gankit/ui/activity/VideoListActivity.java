package com.pinger.gankit.ui.activity;

/*
 *  @项目名：  GankIT 
 *  @包名：    ui.activity
 *  @文件名:   VideoListActivity
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 15:18
 *  @描述：    视频专题列表界面
 */

import android.os.Bundle;

import com.pinger.gankit.R;
import com.pinger.gankit.base.SwipeBackActivity;
import com.pinger.gankit.presenter.VideoListPresenter;
import com.pinger.gankit.ui.view.VideoListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoListActivity extends SwipeBackActivity {

    @BindView(R.id.videoListView)
    VideoListView mVideoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        unbinder = ButterKnife.bind(this);
        mPresenter = new VideoListPresenter(mVideoListView, getIntentData());
    }

    private String getIntentData() {
        mVideoListView.showTitle(getIntent().getStringExtra("title"));
        return getIntent().getStringExtra("catalogId");
    }
}
