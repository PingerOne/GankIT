package com.pinger.gankit.app;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.app
 *  @文件名:   Constant
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/1 21:59
 *  @描述：    常量类
 */

import java.io.File;

public class Constant {

    public static final String PRIMARY_COLOR = "primary_color";
    public static final String TITLE_COLOR = "title_color";

    // 缓存路径
    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + File.separator + "NetCache";

    public static final String FINDVIEW_PAGE = "page";
    public static final String USER_NICK_NAME = "user_nick_name";
    public static final String USER_AVATER = "avater";
    public static final String IS_LOGIN = "is_login";
}
