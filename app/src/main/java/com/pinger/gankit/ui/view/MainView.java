package com.pinger.gankit.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pinger.gankit.R;
import com.pinger.gankit.app.App;
import com.pinger.gankit.app.Constant;
import com.pinger.gankit.base.RootView;
import com.pinger.gankit.manager.ImageManager;
import com.pinger.gankit.presenter.contact.MainContact;
import com.pinger.gankit.ui.activity.FuliActivity;
import com.pinger.gankit.ui.activity.GankActivity;
import com.pinger.gankit.ui.activity.MainActivity;
import com.pinger.gankit.ui.activity.NewsActivity;
import com.pinger.gankit.ui.activity.SettingActivity;
import com.pinger.gankit.ui.adapter.ContentPagerAdapter;
import com.pinger.gankit.ui.fragment.FindFragment;
import com.pinger.gankit.ui.fragment.HomeFragment;
import com.pinger.gankit.ui.fragment.MineFragment;
import com.pinger.gankit.ui.fragment.SubjectFragment;
import com.pinger.gankit.utils.JumpUtil;
import com.pinger.gankit.utils.SPUtil;
import com.pinger.gankit.utils.ThemeUtil;
import com.pinger.gankit.widget.ResideMenu;
import com.pinger.gankit.widget.UnScrollViewPager;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/*
 *  @项目名：  GankIT 
 *  @包名：    ui.view
 *  @文件名:   MainView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/1 19:51
 *  @描述：    MainActivity对应的View层，负责所有界面显示的工作
 */

public class MainView extends RootView<MainContact.Presenter> implements MainContact.View, BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final long WAIT_TIME = 2000;
    private CircleImageView mIvAvatar;
    private TextView mTvDesc;
    private TextView mTvGank;
    private TextView mTvNews;
    private TextView mTvShare;
    private TextView mTvTheme;
    private TextView mTvSetting;
    private TextView mTvVideo;
    private TextView mTvAbout;
    private TextView mTvExit;
    private TextView mTvFuli;
    @BindView(R.id.usViewPager)
    UnScrollViewPager mUsViewPager;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView mBottomNavigationView;
    private MainActivity mActivity;
    public static final String Banner_Stop = "banner_stop";
    private ResideMenu mResideMenu;

    public MainView(Context context) {
        this(context, null);
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.view_main, this); // 填充布局
    }

    @Override
    protected void initView() {
        mActivity = (MainActivity) mContext;

        mResideMenu = new ResideMenu(mActivity, R.layout.menu_left, R.layout.menu_right);
        mResideMenu.attachToActivity(mActivity);
        mResideMenu.setScaleValue(0.5f);

        View leftMenuView = mResideMenu.getLeftMenuView();
        mIvAvatar = (CircleImageView) leftMenuView.findViewById(R.id.iv_avatar);
        mTvDesc = (TextView) leftMenuView.findViewById(R.id.tv_desc);
        mTvVideo = (TextView) leftMenuView.findViewById(R.id.tv_video);
        mTvGank = (TextView) leftMenuView.findViewById(R.id.tv_gank);
        mTvNews = (TextView) leftMenuView.findViewById(R.id.tv_news);
        mTvAbout = (TextView) leftMenuView.findViewById(R.id.tv_about);
        mTvShare = (TextView) leftMenuView.findViewById(R.id.tv_share);
        mTvTheme = (TextView) leftMenuView.findViewById(R.id.tv_theme);
        mTvSetting = (TextView) leftMenuView.findViewById(R.id.tv_setting);
        mTvExit = (TextView) leftMenuView.findViewById(R.id.tv_exit);
        mTvFuli = (TextView) leftMenuView.findViewById(R.id.tv_fuli);

        // 设置ViewPager，填充Fragment
        List<Fragment> mData = getFragments();
        ContentPagerAdapter adapter = new ContentPagerAdapter(mContext, mActivity.getSupportFragmentManager(), mData);
        mUsViewPager.setAdapter(adapter);
        mUsViewPager.setScrollable(false);
        mUsViewPager.setOffscreenPageLimit(mData.size());

        // 更换主题图标
        ThemeUtil.setIconDrawable(mContext, mTvVideo, MaterialDesignIconic.Icon.gmi_videocam, 16, 10);
        ThemeUtil.setIconDrawable(mContext, mTvGank, MaterialDesignIconic.Icon.gmi_android, 16, 10);
        ThemeUtil.setIconDrawable(mContext, mTvNews, MaterialDesignIconic.Icon.gmi_open_in_new, 16, 10);
        ThemeUtil.setIconDrawable(mContext, mTvAbout, MaterialDesignIconic.Icon.gmi_pages, 16, 10);
        ThemeUtil.setIconDrawable(mContext, mTvShare, MaterialDesignIconic.Icon.gmi_share, 16, 10);
        ThemeUtil.setIconDrawable(mContext, mTvSetting, MaterialDesignIconic.Icon.gmi_settings, 16, 10);
        ThemeUtil.setIconDrawable(mContext, mTvTheme, MaterialDesignIconic.Icon.gmi_palette, 16, 10);
        ThemeUtil.setIconDrawable(mContext, mTvExit, MaterialDesignIconic.Icon.gmi_fullscreen_exit, 16, 10);
        ThemeUtil.setIconDrawable(mContext, mTvFuli, MaterialDesignIconic.Icon.gmi_gif, 16, 10);


        if (!SPUtil.getBoolean(mActivity, Constant.IS_LOGIN, false)) {
            mIvAvatar.setImageResource(R.mipmap.user_unknow);
            mTvDesc.setText(mContext.getString(R.string.please_login));
            return;
        }

        mTvDesc.setText(SPUtil.getString(mActivity, Constant.USER_NICK_NAME));
        ImageManager.load(mActivity, SPUtil.getString(mActivity, Constant.USER_AVATER), mIvAvatar);
    }


    @Override
    protected void initEvent() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        mTvVideo.setOnClickListener(this);
        mTvGank.setOnClickListener(this);
        mTvNews.setOnClickListener(this);
        mTvAbout.setOnClickListener(this);
        mTvShare.setOnClickListener(this);
        mTvTheme.setOnClickListener(this);
        mTvSetting.setOnClickListener(this);
        mTvExit.setOnClickListener(this);
        mTvFuli.setOnClickListener(this);

        mResideMenu.setMenuListener(new ResideMenu.OnMenuListener() {
            @Override
            public void openMenu() {
                // 停止轮播
                postBannerState(true);
            }

            @Override
            public void closeMenu() {
                postBannerState(false);
            }
        });
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new SubjectFragment());
        fragments.add(new FindFragment());
        fragments.add(new MineFragment());
        return fragments;
    }

    @Override
    public void setPresenter(MainContact.Presenter presenter) {
        // Java中的检查机制Preconditions
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    private void postBannerState(final boolean stop) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 延时发送通知
                EventBus.getDefault().post(stop, Banner_Stop);
            }
        }, WAIT_TIME);
    }

    @Override
    public void showError(String msg) {
        Snackbar.make(mBottomNavigationView, "出错拉拉拉...", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_home:
                mUsViewPager.setCurrentItem(0, false);
                break;
            case R.id.menu_subject:
                mUsViewPager.setCurrentItem(1, false);
                break;
            case R.id.menu_find:
                mUsViewPager.setCurrentItem(2, false);
                break;
            case R.id.menu_mine:
                mUsViewPager.setCurrentItem(3, false);
                break;
        }
        return false;
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_video:
                mResideMenu.closeMenu();
                break;
            case R.id.tv_gank:
                // 跳转到Gank页面
                JumpUtil.jumpActivity(mContext, GankActivity.class);
                break;

            case R.id.tv_news:
                // 跳转到新闻页面
                JumpUtil.jumpActivity(mContext, NewsActivity.class);
                break;
            case R.id.tv_fuli:
                // 跳转到福利页面
                JumpUtil.jumpActivity(mContext, FuliActivity.class);
                break;
            case R.id.tv_about:
                new MaterialDialog.Builder(mActivity)
                        .title(R.string.about)
                        .icon(new IconicsDrawable(mActivity)
                                .color(ThemeUtil.getThemeColor(mActivity, R.attr.colorPrimary))
                                .icon(MaterialDesignIconic.Icon.gmi_account)
                                .sizeDp(20))
                        .content(R.string.about_me)
                        .positiveText(R.string.close)
                        .show();
                break;
            case R.id.tv_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.setting_recommend_content));
                shareIntent.setType("text/plain");

                // 设置分享列表的标题，并且每次都显示分享列表
                mContext.startActivity(Intent.createChooser(shareIntent, "分享到"));
                break;
            case R.id.tv_setting:
                // 跳转到设置
                JumpUtil.jumpActivity(mContext, SettingActivity.class);
                break;
            case R.id.tv_theme:
                // 设置主题
                new ColorChooserDialog.Builder(mActivity, R.string.theme)
                        .customColors(R.array.colors, null)
                        .doneButton(R.string.done)
                        .cancelButton(R.string.cancel)
                        .allowUserColorInput(false)
                        .allowUserColorInputAlpha(false)
                        .show();
                break;
            case R.id.tv_exit:
                App.getInstance().exitApp();
                break;
        }
    }

    public ResideMenu getResideMenu() {
        return mResideMenu;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
