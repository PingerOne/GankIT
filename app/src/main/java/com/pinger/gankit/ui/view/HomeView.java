package com.pinger.gankit.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.jude.rollviewpager.hintview.IconHintView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pinger.gankit.R;
import com.pinger.gankit.base.RootView;
import com.pinger.gankit.model.bean.VideoInfo;
import com.pinger.gankit.model.bean.VideoRes;
import com.pinger.gankit.presenter.contact.HomeContact;
import com.pinger.gankit.ui.activity.MainActivity;
import com.pinger.gankit.ui.adapter.BannerAdapter;
import com.pinger.gankit.ui.adapter.HomedAdapter;
import com.pinger.gankit.utils.JumpUtil;
import com.pinger.gankit.utils.ScreenUtil;
import com.pinger.gankit.widget.ResideMenu;
import com.pinger.gankit.widget.RollPagerView;
import com.pinger.gankit.widget.theme.ColorRelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *  @项目名：  GankIT 
 *  @包名：    ui.view
 *  @文件名:   HomeView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/2 20:43
 *  @描述：    主页的View层
 */

public class HomeView extends RootView<HomeContact.Presenter> implements HomeContact.View, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    @BindView(R.id.tv_name)
    TextView mTitleName;
    @BindView(R.id.title)
    ColorRelativeLayout mTitle;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.iv_icon)
    ImageView mIvHome;
    HomedAdapter mAdapter;
    RollPagerView mBanner;
    View mHeaderView;
    TextView mEtSearchKey;
    RelativeLayout mRlGoSearch;
    private MainActivity mActivity;
    private ResideMenu mResideMenu;

    public HomeView(Context context) {
        this(context, null);
    }

    public HomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.view_home, this);
    }

    @Override
    protected void initView() {
        mActivity = (MainActivity) mContext;
        mTitle.setVisibility(View.GONE);
        mTitleName.setText(mActivity.getString(R.string.video_jingxuan));
        mIvHome.setImageDrawable(new IconicsDrawable(mContext).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_apps).sizeDp(18));
        mHeaderView = LayoutInflater.from(mContext).inflate(R.layout.home_header, null);
        mBanner = ButterKnife.findById(mHeaderView, R.id.banner);
        mRlGoSearch = ButterKnife.findById(mHeaderView, R.id.rlGoSearch);
        mEtSearchKey = ButterKnife.findById(mHeaderView, R.id.etSearchKey);
        mBanner.setPlayDelay(2000);

        mAdapter = new HomedAdapter(getContext());
        mRecyclerView.setAdapterWithProgress(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setErrorView(R.layout.view_error);
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(getContext(), 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        mRecyclerView.addItemDecoration(itemDecoration);

        mResideMenu = mActivity.getResideMenu();
        mResideMenu.addIgnoredView(mBanner);
    }

    @Override
    protected void initEvent() {
        // fab点击事件，返回最上页面
        mFab.setOnClickListener(view -> mRecyclerView.scrollToPosition(0));

        // 设置下拉刷新
        mRecyclerView.setRefreshListener(this);
        // 设置滑动事件，实现渐变效果
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (getHeaderScrollHeight() <= ScreenUtil.dip2px(mContext, 150)) {
                    new Handler().postAtTime(() -> {
                        float percentage = (float) getHeaderScrollHeight() / ScreenUtil.dip2px(mContext, 150);
                        mTitle.setAlpha(percentage);
                        if (percentage > 0)
                            mTitle.setVisibility(View.VISIBLE);
                        else
                            mTitle.setVisibility(View.GONE);

                    }, 300);
                } else {
                    mTitle.setAlpha(1.0f);
                    mTitle.setVisibility(View.VISIBLE);
                }
            }
        });
        // 错误视图点击事件
        mRecyclerView.getErrorView().setOnClickListener(view -> {
            mRecyclerView.showProgress();
            onRefresh();
        });

        // 设置条目点击事件
        mAdapter.setOnItemClickListener(position -> JumpUtil.jump2VideoDetailActivity(mContext, mAdapter.getItem(position)));

        // 设置搜索点击事件
        mRlGoSearch.setOnClickListener(view -> {
//                Intent intent = new Intent(mContext, SearchActivity.class);
//                intent.putExtra("videoInfos", (Serializable) videos);
//                mContext.startActivity(intent);
            Toast.makeText(mContext, "正在努力开发中...", Toast.LENGTH_SHORT).show();
        });
        mIvHome.setOnClickListener(view -> {
            if (mResideMenu.isOpened()) {
                mResideMenu.closeMenu();
            } else {
                mResideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
    }


    /**
     * 由Presenter传进来，显示数据
     *
     * @param videoRes
     */
    @Override
    public void showContent(final VideoRes videoRes) {
        if (videoRes != null) {
            mAdapter.clear();
            List<VideoInfo> videoInfos;

            for (int i = 1; i < videoRes.list.size(); i++) {
                if (videoRes.list.get(i).title.equals(mContext.getString(R.string.video_jingcai))) {
                    videoInfos = videoRes.list.get(i).childList;
                    mAdapter.addAll(videoInfos);
                    break;
                }
            }

            if (mAdapter.getHeaderCount() == 0) {
                mAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
                    @Override
                    public View onCreateView(ViewGroup parent) {
                        mBanner.setHintView(new IconHintView(getContext(), R.mipmap.ic_point_select, R.mipmap.ic_point_normal, ScreenUtil.dip2px(getContext(), 10)));
                        mBanner.setHintPadding(0, 0, 0, ScreenUtil.dip2px(getContext(), 8));
                        mBanner.setAdapter(new BannerAdapter(getContext(), videoRes.list.get(0).childList));
                        return mHeaderView;
                    }

                    @Override
                    public void onBindView(View headerView) {

                    }
                });
            }
        }
    }


    @Override
    public void stopBanner(boolean stop) {
        if (stop) {
            mBanner.pause();
        } else {
            mBanner.resume();
        }
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void setPresenter(HomeContact.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }


    @Override
    public void refreshFail(String msg) {
        if (!TextUtils.isEmpty(msg))
            showError(msg);
        mRecyclerView.showError();
    }

    @Override
    public void showError(String msg) {
        Snackbar.make(mRecyclerView, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        // 刷新数据交给Presenter去处理
        mPresenter.onRefresh();
    }

    /**
     * 获取顶部滑动的高度
     *
     * @return
     */
    private int getHeaderScrollHeight() {
        if (mHeaderView == null) {
            return 0;
        }
        int distance = mHeaderView.getTop();
        distance = Math.abs(distance);
        return distance;
    }
}
