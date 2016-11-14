package com.pinger.gankit.ui.fragment;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.fragment
 *  @文件名:   VideoCommentFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/7 23:52
 *  @描述：    视频详情页面的评论内容对应的Fragment
 */

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.VideoCommentPresenter;
import com.pinger.gankit.ui.view.VideoCommentView;

import butterknife.BindView;

public class VideoCommentFragment extends BaseFragment {

    @BindView(R.id.commentView)
    VideoCommentView mCommentView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_video_comment;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new VideoCommentPresenter(mCommentView);
    }

    @Override
    protected void loadData() {
        ((VideoCommentPresenter) mPresenter).onRefresh();
    }
}
