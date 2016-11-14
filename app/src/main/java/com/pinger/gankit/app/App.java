package com.pinger.gankit.app;

import android.app.Activity;
import android.app.Application;

import com.lzy.ninegrid.NineGridView;
import com.pinger.gankit.manager.ImageManager;

import java.util.HashSet;
import java.util.Set;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit
 *  @文件名:   App
 *  @创建者:   Pinger
 *  @创建时间:  2016/10/22 19:14
 *  @描述：    应用的App类，初始化全局操作，需要在清单文件中注册才有效
 */

public class App extends Application {

    private static App instance;

    /**
     * 保证唯一
     */
    private Set<Activity> allActivities;

    /**
     * 同步单利
     *
     * @return
     */
    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initNineGridView();
    }

    /**
     * 初始化新闻图片加载器
     */
    private void initNineGridView() {
        NineGridView.setImageLoader(ImageManager.getsInstance().new GlideImageLoader());
    }


    /**
     * 注册Activity，方便APP管理所有的Activity
     *
     * @param act
     */
    public void registerActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    /**
     * 注销Activity
     *
     * @param act
     */
    public void unregisterActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    /**
     * 清空Activity集合，退出
     */
    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    if (act != null && !act.isFinishing())
                        act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
