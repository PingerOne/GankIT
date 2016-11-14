package com.pinger.gankit.manager;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.manager
 *  @文件名:   ImageManager
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 13:56
 *  @描述：    封装Glide，用来加载图片等操作
 */


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.ninegrid.NineGridView;

public class ImageManager {

    private ImageManager() {
    }

    private static ImageManager sInstance;

    public static ImageManager getsInstance() {
        if (sInstance == null) {
            synchronized (ImageManager.class) {
                if (sInstance == null) {
                    sInstance = new ImageManager();
                }
            }
        }

        return sInstance;
    }


    public void load(Context context, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
    }

    public void load(Activity activity, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        if (!activity.isDestroyed()) {
            Glide.with(activity).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
    }

    /**
     * Glide 加载
     */
    public class GlideImageLoader implements NineGridView.ImageLoader {

        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            load(context, url, imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }

}
