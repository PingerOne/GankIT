package com.pinger.gankit.ui.activity;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pinger.gankit.R;
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
        tvName.setText(getString(R.string.news));
        ivIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_open_in_new).sizeDp(20));
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
