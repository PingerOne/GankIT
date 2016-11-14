package com.pinger.gankit.utils;


/*
 *  @文件名:   PrefUtil
 *  @创建者:   Pinger
 *  @创建时间:  2016/10/21 10:36
 *  @描述：    对SharedPreference操做的工具类，文件名字默认为config
 */

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {

    private SPUtil() {
    }

    private static SharedPreferences getSP(Context context) {
        return context.getSharedPreferences("config", Context.MODE_PRIVATE);
    }

    // =====================Boolean=====================
    public static void putBoolean(Context context, String key, boolean value) {
        getSP(context).edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, true);
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return getSP(context).getBoolean(key, defValue);
    }

    // =====================String=====================
    public static void putString(Context context, String key, String value) {
        getSP(context).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key) {
        return getString(context, key, "");
    }

    public static String getString(Context context, String key, String defValue) {
        return getSP(context).getString(key, defValue);
    }

    // =====================Int=====================
    public static void putInt(Context context, String key, int value) {
        getSP(context).edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key) {
        return getInt(context, key, 0);
    }

    public static int getInt(Context context, String key, int defValue) {
        return getSP(context).getInt(key, defValue);
    }

    // =====================Long=====================
    public static void putLong(Context context, String key, long value) {
        getSP(context).edit().putLong(key, value).apply();
    }

    public static long getLong(Context context, String key) {
        return getLong(context, key, 0);
    }

    public static long getLong(Context context, String key, long defValue) {
        return getSP(context).getLong(key, defValue);
    }

    // =====================contains=====================
    public static boolean contains(Context context, String key) {
        return getSP(context).contains(key);
    }

    // =====================remove=====================
    public static boolean remove(Context context, String key) {
        return getSP(context).edit().remove(key).commit();
    }

    // =====================拓展=====================

    /**
     * 是否第一次进入应用
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean isFirstTime(Context context, String key) {
        if (getBoolean(context, key, false)) {
            return false;
        } else {
            putBoolean(context, key, true);
            return true;
        }
    }
}
