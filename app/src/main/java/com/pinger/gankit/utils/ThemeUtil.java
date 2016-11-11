package com.pinger.gankit.utils;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.utils
 *  @文件名:   ThemeUtil
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/1 20:58
 *  @描述：    与主题相关的设置
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.widget.TextView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.pinger.gankit.R;
import com.pinger.gankit.widget.theme.Theme;

import static com.pinger.gankit.utils.SPUtil.getString;
import static com.pinger.gankit.utils.SPUtil.putString;

public class ThemeUtil {

    /**
     * 设置文字的Icon
     *
     * @param mContext
     * @param view
     * @param icon
     * @param size
     * @param padding
     */
    public static void setIconDrawable(Context mContext, TextView view, IIcon icon, int size, int padding) {
        view.setCompoundDrawablesWithIntrinsicBounds(new IconicsDrawable(mContext)
                        .icon(icon)
                        .color(Color.WHITE)
                        .sizeDp(size),
                null, null, null);
        view.setCompoundDrawablePadding(ScreenUtil.dip2px(mContext, padding));
    }


    /**
     * 设置文字Icon
     *
     * @param mContext
     * @param view
     * @param icon
     */
    public static void setIconDrawable(Context mContext, TextView view, IIcon icon) {
        view.setCompoundDrawablesWithIntrinsicBounds(new IconicsDrawable(mContext)
                        .icon(icon)
                        .color(getThemeColor(mContext, R.attr.colorPrimary))
                        .sizeDp(14),
                null, null, null);
        view.setCompoundDrawablePadding(ScreenUtil.dip2px(mContext, 5));
    }


    /**
     * 设置获取当前的主题
     *
     * @param context
     * @return
     */
    public static Theme getCurrentTheme(Context context) {
        // 一上来默认显示蓝色背景
        return Theme.valueOf(getString(context, "app_theme", Theme.Blue.name()));
    }

    public static void setCurrentTheme(Context context, Theme currentTheme) {
        putString(context, "app_theme", currentTheme.name());
    }


    /**
     * 获取主题颜色
     *
     * @param context
     * @param attrRes
     * @return
     */
    public static int getThemeColor(Context context, int attrRes) {
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{attrRes});
        int color = typedArray.getColor(0, 0xffffff);
        typedArray.recycle();
        return color;
    }
}
