package com.pinger.gankit.widget;
/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.widght
 *  @文件名:   UnScrollViewPager
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/1 20:08
 *  @描述：可以指定是否滑动的View Pager
 */


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Scroller;

public class UnScrollViewPager extends ViewPager {

    private boolean isScrollable = false;
    private Context mContext;

    public UnScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnScrollViewPager(Context context) {
        super(context);
        this.mContext = context;
        Scroller scroller = new Scroller(mContext);

    }

    /**
     * 指定是否滑动，默认不可以滑动
     * @param scrollable
     */
    public void setScrollable(boolean scrollable) {
        isScrollable = scrollable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (isScrollable)
            return super.onTouchEvent(arg0);
        boolean b = super.onTouchEvent(arg0);
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (isScrollable)
            return super.onInterceptTouchEvent(arg0);
        return false;
    }
}