package com.pinger.gankit.model.bean;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.model.bean
 *  @文件名:   AppInfo
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 14:51
 *  @描述：    应用信息Mode类
 */

import android.graphics.drawable.Drawable;

public class AppInfo {


    private static final long serialVersionUID = 1L;
    private String appClass;
    private Drawable appIcon;
    private String appLable;
    private String appPackage;

    public String getAppClass() {
        return appClass;
    }

    public void setAppClass(String appClass) {
        this.appClass = appClass;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppLable() {
        return appLable;
    }

    public void setAppLable(String appLable) {
        this.appLable = appLable;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }
}
