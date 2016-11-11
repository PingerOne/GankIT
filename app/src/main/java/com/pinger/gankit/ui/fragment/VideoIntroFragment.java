package com.pinger.gankit.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.model.bean.VideoRes;
import com.pinger.gankit.presenter.VideoDetailPresenter;
import com.pinger.gankit.ui.adapter.VideoIntroAdapter;
import com.pinger.gankit.utils.JumpUtil;
import com.pinger.gankit.utils.ScreenUtil;
import com.pinger.gankit.utils.StringUtil;
import com.pinger.gankit.widget.TextViewExpandableAnimation;

import org.simple.eventbus.Subscriber;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pinger.gankit.R.id.recyclerView;

/*
 *  @项目名：  GankIT
 *  @包名：    com.pinger.gankit.ui.fragment
 *  @文件名:   VideoIntroFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/7 23:51
 *  @描述：    视频详情页面的简介内容对应的Fragment
 */


public class VideoIntroFragment extends BaseFragment {
    @BindView(recyclerView)
    EasyRecyclerView mRecyclerView;
    private View mHeadView;
    private TextViewExpandableAnimation mTextViewExpandable;
    private VideoIntroAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_video_intro;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        // 将描述信息作为RecyclerView的头部添加进来
        mHeadView = View.inflate(mContext,R.layout.intro_header, null);
        mTextViewExpandable = ButterKnife.findById(mHeadView, R.id.textViewExpandable);

        // 初始化RecyclerView
        mAdapter = new VideoIntroAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        gridLayoutManager.setSpanSizeLookup(mAdapter.obtainGridSpanSizeLookUp(3));
        mRecyclerView.setLayoutManager(gridLayoutManager);
        // 设置条目间隙
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(getContext(), 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        mRecyclerView.addItemDecoration(itemDecoration);

        // 条目点击事件
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 跳转到播放页面
                JumpUtil.jump2VideoDetailActivity(getContext(), mAdapter.getItem(position));
                getActivity().finish();
            }
        });

        // 添加标题View
        mAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return mHeadView;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });

    }

    @Override
    protected void loadData() {

    }


    /**
     * 由于简介内容的数据已经在视频详情的Presenter中拿到了，所以只需要将数据传递过来就好了
     * @param videoRes
     */
    @Subscriber(tag = VideoDetailPresenter.Refresh_Video_Info)
    public void initData(VideoRes videoRes) {
        // 获取到数据，进行显示
        String dir = "导演：" + StringUtil.removeOtherCode(videoRes.director);
        String act = "主演：" + StringUtil.removeOtherCode(videoRes.actors);
        String desc = dir + "\n" + act + "\n" + "简介：" + StringUtil.removeOtherCode(videoRes.description);
        mTextViewExpandable.setText(desc);
        if (videoRes.list.size() > 1)
            mAdapter.addAll(videoRes.list.get(1).childList);
        else
            mAdapter.addAll(videoRes.list.get(0).childList);
    }

}
