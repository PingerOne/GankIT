package com.pinger.gankit.utils;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.utils
 *  @文件名:   JumpUtil
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 15:15
 *  @描述：    跳转Activity工具类
 */

import android.content.Context;
import android.content.Intent;

import com.pinger.gankit.model.bean.VideoInfo;
import com.pinger.gankit.ui.activity.VideoDetailActivity;
import com.pinger.gankit.ui.activity.VideoListActivity;
import com.pinger.gankit.ui.activity.WebActivity;

public class JumpUtil {
    private JumpUtil() {
    }

    public static void jump2VideoDetailActivity(Context context, VideoInfo videoInfo) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtra("videoInfo", videoInfo);
        context.startActivity(intent);
    }

    public static void jump2VideoListActivity(Context context, String catalogId, String title) {
        Intent intent = new Intent(context, VideoListActivity.class);
        intent.putExtra("catalogId", catalogId);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }


    public static void jumpActivity(Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    public static void jump2WebActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(WebActivity.WEB_TITLE, title);
        intent.putExtra(WebActivity.WEB_URL, url);
        context.startActivity(intent);
    }
}
