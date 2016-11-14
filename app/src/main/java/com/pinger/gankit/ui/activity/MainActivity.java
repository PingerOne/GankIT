package com.pinger.gankit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.pinger.gankit.R;
import com.pinger.gankit.app.App;
import com.pinger.gankit.app.Constant;
import com.pinger.gankit.base.BaseActivity;
import com.pinger.gankit.ui.view.MainView;
import com.pinger.gankit.utils.SPUtil;
import com.pinger.gankit.utils.ThemeUtil;
import com.pinger.gankit.widget.ResideMenu;
import com.pinger.gankit.widget.theme.Theme;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.simple.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements ColorChooserDialog.ColorCallback, IUiListener {

    public static final String Set_Theme_Color = "Set_Theme_Color";
    public static final String LOGIND_RESULT = "Login_Result";
    private Long firstTime = 0L;
    @BindView(R.id.main_view)
    @Nullable
    MainView mMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }


    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {
        if (selectedColor == ThemeUtil.getThemeColor(this, R.attr.colorPrimary))
            return;

        if (selectedColor == getResources().getColor(R.color.colorBluePrimary)) {
            this.setTheme(R.style.BlueTheme);
            ThemeUtil.setCurrentTheme(this, Theme.Blue);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "#2196F3");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorRedPrimary)) {
            this.setTheme(R.style.RedTheme);
            ThemeUtil.setCurrentTheme(this, Theme.Red);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "#F44336");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorBrownPrimary)) {
            this.setTheme(R.style.BrownTheme);
            ThemeUtil.setCurrentTheme(this, Theme.Brown);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "#795548");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorGreenPrimary)) {
            this.setTheme(R.style.GreenTheme);
            ThemeUtil.setCurrentTheme(this, Theme.Green);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "#4CAF50");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorPurplePrimary)) {
            this.setTheme(R.style.PurpleTheme);
            ThemeUtil.setCurrentTheme(this, Theme.Purple);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "#9c27b0");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorTealPrimary)) {
            this.setTheme(R.style.TealTheme);
            ThemeUtil.setCurrentTheme(this, Theme.Teal);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "#009688");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorPinkPrimary)) {
            this.setTheme(R.style.PinkTheme);
            ThemeUtil.setCurrentTheme(this, Theme.Pink);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "#E91E63");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorDeepPurplePrimary)) {
            this.setTheme(R.style.DeepPurpleTheme);
            ThemeUtil.setCurrentTheme(this, Theme.DeepPurple);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "#673AB7");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorOrangePrimary)) {
            this.setTheme(R.style.OrangeTheme);
            ThemeUtil.setCurrentTheme(this, Theme.Orange);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "#FF9800");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorIndigoPrimary)) {
            this.setTheme(R.style.IndigoTheme);
            ThemeUtil.setCurrentTheme(this, Theme.Indigo);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "#3F51B5");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorLightGreenPrimary)) {
            this.setTheme(R.style.LightGreenTheme);
            ThemeUtil.setCurrentTheme(this, Theme.LightGreen);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "#8BC34A");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorDeepOrangePrimary)) {
            this.setTheme(R.style.DeepOrangeTheme);
            ThemeUtil.setCurrentTheme(this, Theme.DeepOrange);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "##FF5722");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorLimePrimary)) {
            this.setTheme(R.style.LimeTheme);
            ThemeUtil.setCurrentTheme(this, Theme.Lime);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "#CDDC39");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorBlueGreyPrimary)) {
            this.setTheme(R.style.BlueGreyTheme);
            ThemeUtil.setCurrentTheme(this, Theme.BlueGrey);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "#CDDC39");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorCyanPrimary)) {
            this.setTheme(R.style.CyanTheme);
            ThemeUtil.setCurrentTheme(this, Theme.Cyan);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "#00BCD4");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(android.R.color.black)) {
            this.setTheme(R.style.BlackTheme);
            ThemeUtil.setCurrentTheme(this, Theme.Black);
            SPUtil.putString(this, Constant.PRIMARY_COLOR, "#000000");
            SPUtil.putString(this, Constant.TITLE_COLOR, "#0aa485");
        }
        // 发送一个事件出去,修改颜色
        EventBus.getDefault().post(selectedColor, Set_Theme_Color);
    }

    @Override
    public void onBackPressed() {
        if (mMainView.getResideMenu().isOpened()) {
            mMainView.getResideMenu().closeMenu();
        } else {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 1500) {
                Snackbar.make(mMainView, getString(R.string.exit), Snackbar.LENGTH_SHORT)
                        .show();
                firstTime = secondTime;
            } else {
                App.getInstance().exitApp();
            }
        }
    }

    public ResideMenu getResideMenu() {
        return mMainView.getResideMenu();
    }

    /**
     * QQ客户端授权登录，必须重写
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, this);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 授权完成
     *
     * @param result
     */
    @Override
    public void onComplete(Object result) {
        EventBus.getDefault().post(result, LOGIND_RESULT);
    }

    /**
     * 授权失败
     *
     * @param uiError
     */
    @Override
    public void onError(UiError uiError) {

    }

    /**
     * 授权取消
     */
    @Override
    public void onCancel() {

    }
}
