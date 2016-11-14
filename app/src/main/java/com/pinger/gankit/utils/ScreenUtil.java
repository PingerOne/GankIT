package com.pinger.gankit.utils;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.utils
 *  @文件名:   ScreenUtil
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/1 19:43
 *  @描述：    屏幕相关属性
 */


import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtil {
    private ScreenUtil(){}

    /**
     * dp2px
     */
    public static int dip2px(Context ctx, float dpValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * px2dp
     */
    public static int px2dip(Context ctx, float pxValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * screenWidth
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * screenHeight
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
}
