package com.pinger.gankit.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeFrameLayout;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pinger.gankit.R;
import com.pinger.gankit.app.Constant;
import com.pinger.gankit.base.RootView;
import com.pinger.gankit.model.bean.VideoRes;
import com.pinger.gankit.model.bean.VideoType;
import com.pinger.gankit.presenter.contact.FindContact;
import com.pinger.gankit.ui.activity.MainActivity;
import com.pinger.gankit.ui.adapter.SwipeDeckAdapter;
import com.pinger.gankit.utils.SPUtil;
import com.pinger.gankit.utils.ScreenUtil;
import com.pinger.gankit.widget.ProgressView;
import com.pinger.gankit.widget.ResideMenu;
import com.pinger.gankit.widget.SwipeDeck;
import com.pinger.gankit.widget.theme.ColorTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.view
 *  @文件名:   FindView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/5 22:22
 *  @描述：    发现页面的View层
 */

public class FindView extends RootView<FindContact.Presenter> implements FindContact.View {

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_nomore)
    TextView mTvNomore;
    @BindView(R.id.swipe_deck)
    SwipeDeck mSwipeDeck;
    @BindView(R.id.btn_next)
    ColorTextView mBtnNext;
    @BindView(R.id.swipeLayout)
    SwipeFrameLayout mSwipeLayout;
    @BindView(R.id.loading)
    ProgressView mLoading;

    private List<VideoType> videoTypeList = new ArrayList<>();
    private SwipeDeckAdapter mAdapter;
    private ResideMenu mResideMenu;

    public FindView(Context context) {
        this(context, null);
    }

    public FindView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.view_find, this);
    }


    @Override
    public void showContent(VideoRes videoRes) {
        if (videoRes != null) {
            videoTypeList.clear();
            videoTypeList.addAll(videoRes.list);
            mSwipeDeck.removeAllViews();
            mSwipeDeck.removeAllViews();
            mAdapter = new SwipeDeckAdapter(videoTypeList, getContext());
            mSwipeDeck.setAdapter(mAdapter);
            mTvNomore.setVisibility(View.VISIBLE);
            mBtnNext.setVisibility(VISIBLE);
        }
    }


    @Override
    protected void initView() {
        MainActivity activity = (MainActivity) mContext;
        mTvName.setText(mContext.getString(R.string.video_faxian));
        mIvIcon.setImageDrawable(new IconicsDrawable(mContext).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_search_in_page).sizeDp(20));

        ViewGroup.LayoutParams params = mSwipeDeck.getLayoutParams();
        // 屏幕高度的2/3
        params.height = ScreenUtil.getScreenHeight(getContext()) / 3 * 2;
        mSwipeDeck.setLayoutParams(params);
        // 设置硬件加速可用，更加流畅
        mSwipeDeck.setHardwareAccelerationEnabled(true);

        mResideMenu = activity.getResideMenu();
        mResideMenu.addIgnoredView(mSwipeDeck);
    }


    @Override
    protected void initEvent() {
        mSwipeDeck.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {

            }

            @Override
            public void cardSwipedRight(int position) {

            }

            @Override
            public void cardsDepleted() {
                mSwipeDeck.setVisibility(GONE);
            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {

            }
        });

        mIvIcon.setOnClickListener(view -> {
            if (mResideMenu.isOpened()) {
                mResideMenu.closeMenu();
            } else {
                mResideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });


    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void refreshFail(String msg) {
        hideLoading();
        if (!TextUtils.isEmpty(msg)) {
            showError(msg);
        }
    }

    @Override
    public int getLastPage() {
        return SPUtil.getInt(mContext, Constant.FINDVIEW_PAGE, 1);
    }

    @Override
    public void setLastPage(int page) {
        SPUtil.putInt(getContext(), Constant.FINDVIEW_PAGE, page);
    }

    @Override
    public void hideLoading() {
        mLoading.setVisibility(View.GONE);
    }

    @Override
    public void setPresenter(FindContact.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.tv_nomore, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_nomore:
            case R.id.btn_next:
                nextCard();
                break;
        }
    }

    /**
     * 下一个卡片
     */
    private void nextCard() {
        mSwipeDeck.setVisibility(View.VISIBLE);
        mLoading.setVisibility(View.VISIBLE);
        mTvNomore.setVisibility(View.GONE);
        mPresenter.requestData();
    }
}
