package com.pinger.gankit.utils;/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.utils
 *  @文件名:   StringUtil
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 15:27
 *  @描述：    字符串相关操作类
 */

import android.text.TextUtils;

import java.util.Random;

public class StringUtil {

    /**
     * 移除特殊字符
     *
     * @param s
     * @return
     */
    public static String removeOtherCode(String s) {
        if (TextUtils.isEmpty(s))
            return "";
        s = s.replaceAll("\\<.*?>|\\n", "");
        return s;
    }

    /**
     * 截取错误信息
     *
     * @param msg
     * @return
     */
    public static String getErrorMsg(String msg) {
        if (msg != null) {
            if (msg.contains("*")) {
                msg = msg.substring(msg.indexOf("*") + 1);
                return msg;
            } else
                return "";
        } else {
            return "";
        }
    }

    /**
     * 返回两者之间的随机数
     *
     * @param start
     * @param end
     * @return
     */
    public static int getRandomNumber(int start, int end) {
        return new Random().nextInt(end) % (end - start + 1) + start;
    }

    public static String isEmpty(String content) {
        return TextUtils.isEmpty(content) ? "" : content;
    }

    /**
     * 根据Url获取catalogId
     *
     * @param url
     * @return
     */
    public static String getCatalogId(String url) {
        String catalogId = "";
        if (!TextUtils.isEmpty(url) && url.contains("="))
            catalogId = url.substring(url.lastIndexOf("=") + 1);
        return catalogId;
    }
}
