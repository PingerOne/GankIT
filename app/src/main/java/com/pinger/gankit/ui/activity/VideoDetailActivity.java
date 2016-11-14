package com.pinger.gankit.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pinger.gankit.R;
import com.pinger.gankit.base.SwipeBackActivity;
import com.pinger.gankit.model.bean.VideoInfo;
import com.pinger.gankit.presenter.VideoDetailPresenter;
import com.pinger.gankit.ui.view.VideoDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.activity
 *  @文件名:   VideoDetailActivity
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/6 23:17
 *  @描述：    TODO
 */

public class VideoDetailActivity extends SwipeBackActivity {

    @BindView(R.id.videoDetailView) @Nullable VideoDetailView mVideoDetailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detial);
        unbinder = ButterKnife.bind(this);
        mPresenter = new VideoDetailPresenter(mVideoDetailView, getIntentData());
    }


    /**
     * 获取Intent传递过来的数据
     */
    private VideoInfo getIntentData() {
        return (VideoInfo) getIntent().getSerializableExtra("videoInfo");
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
