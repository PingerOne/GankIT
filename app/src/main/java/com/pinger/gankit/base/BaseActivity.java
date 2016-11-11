package com.pinger.gankit.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.pinger.gankit.R;
import com.pinger.gankit.app.App;
import com.pinger.gankit.utils.ScreenUtil;
import com.pinger.gankit.utils.ThemeUtil;
import com.pinger.gankit.widget.theme.ColorRelativeLayout;
import com.pinger.gankit.widget.theme.Theme;

import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.base
 *  @文件名:   BaseActivity
 *  @创建者:   Pinger
 *  @创建时间:  2016/10/22 19:13
 *  @描述：    基类Activity,  10.25 升级  11.1 重大升级
 */

public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity {

    protected Unbinder unbinder;
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init() {
        setTranslucentStatus();
        initAppTheme();
        // 注册Activity
        App.getInstance().registerActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
       // setTitleHeight(getRootView(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().unregisterActivity(this);
        if (unbinder != null)
            unbinder.unbind();
        mPresenter = null;
    }

    /**
     * 初始化APP主题
     */
    private void initAppTheme() {
        Theme currentTheme = ThemeUtil.getCurrentTheme(this);
        switch (currentTheme) {
            case Blue:
                setTheme(R.style.BlueTheme);
                break;
            case Red:
                setTheme(R.style.RedTheme);
                break;
            case Brown:
                setTheme(R.style.BrownTheme);
                break;
            case Green:
                setTheme(R.style.GreenTheme);
                break;
            case Purple:
                setTheme(R.style.PurpleTheme);
                break;
            case Teal:
                setTheme(R.style.TealTheme);
                break;
            case Pink:
                setTheme(R.style.PinkTheme);
                break;
            case DeepPurple:
                setTheme(R.style.DeepPurpleTheme);
                break;
            case Orange:
                setTheme(R.style.OrangeTheme);
                break;
            case Indigo:
                setTheme(R.style.IndigoTheme);
                break;
            case LightGreen:
                setTheme(R.style.LightGreenTheme);
                break;
            case Lime:
                setTheme(R.style.LimeTheme);
                break;
            case DeepOrange:
                setTheme(R.style.DeepOrangeTheme);
                break;
            case Cyan:
                setTheme(R.style.CyanTheme);
                break;
            case BlueGrey:
                setTheme(R.style.BlueGreyTheme);
                break;
        }
    }

    /**
     * 设置沉浸式
     */
    protected void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 获取根View
     *
     * @param context
     * @return
     */
    protected static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }


    private ProgressDialog dialog;

    protected void showLoading() {
        if (dialog != null && dialog.isShowing()) return;
        dialog = new ProgressDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("正在加载中...");
        dialog.show();
    }

    protected void dismissLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 初始化 Toolbar
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, int resTitle) {
        initToolBar(toolbar, homeAsUpEnabled, getString(resTitle));
    }
}
