package com.pinger.gankit.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pinger.gankit.R;
import com.pinger.gankit.widget.SwipeBackLayout;

/*
 *  @项目名：  GankIT 
 *  @包名：    ui.activity
 *  @文件名:   SwipeBackActivity
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/5 15:54
 *  @描述：    1、想要实现向右滑动删除Activity效果只需要继承SwipeBackActivity即可，如果当前页面含有ViewPager只需要调用SwipeBackLayout的setViewPager()方法即可
 *             2、设置activity的主题为android:theme="@style/CustomTransparent
 */

public abstract class SwipeBackActivity extends BaseActivity {
    protected SwipeBackLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackLayout swipeBackLayout = (SwipeBackLayout) View.inflate(this, R.layout.activity_swipe_back, null);
        swipeBackLayout.attachToActivity(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }
}
