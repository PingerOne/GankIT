package com.pinger.gankit.ui.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.pinger.gankit.base.BaseTabActivity;
import com.pinger.gankit.ui.adapter.BaseTabAdapter;
import com.pinger.gankit.ui.fragment.news.NewsTotFragment;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.activity
 *  @文件名:   NewsActivity
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/9 13:06
 *  @描述：    TODO
 */
public class NewsActivity extends BaseTabActivity {

    @Override
    protected void initToolBar(TextView tvName, ImageView ivIcon) {
        tvName.setText("新闻");
    }

    @Override
    protected int setupWithTopTab(BaseTabAdapter adapter) {
        adapter.addTopTab(NewsTotFragment.class, "国内第一", null);
        adapter.addTopTab(NewsTotFragment.class, "国内第二", null);
        adapter.addTopTab(NewsTotFragment.class, "国内第三", null);
        adapter.addTopTab(NewsTotFragment.class, "国内第四", null);
        adapter.addTopTab(NewsTotFragment.class, "国内第五", null);
        return 5;
    }
}
