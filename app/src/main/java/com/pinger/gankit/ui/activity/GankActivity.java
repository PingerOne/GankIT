package com.pinger.gankit.ui.activity;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.activity
 *  @文件名:   GankActivity
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/6 21:29
 *  @描述：    干货
 */

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseTabActivity;
import com.pinger.gankit.ui.adapter.BaseTabAdapter;
import com.pinger.gankit.ui.fragment.gank.AllFragment;
import com.pinger.gankit.ui.fragment.gank.AndroidFragment;
import com.pinger.gankit.ui.fragment.gank.AppFragment;
import com.pinger.gankit.ui.fragment.gank.FuliFragment;
import com.pinger.gankit.ui.fragment.gank.IOSFragment;
import com.pinger.gankit.ui.fragment.gank.ResourceFragment;
import com.pinger.gankit.ui.fragment.gank.TuiJianFragment;
import com.pinger.gankit.ui.fragment.gank.WebFragment;

public class GankActivity extends BaseTabActivity {

    @Override
    protected void initToolBar(TextView tvName, ImageView ivIcon) {
        tvName.setText(getString(R.string.ganhuo));
        ivIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_android).sizeDp(18));
    }

    @Override
    protected int setupWithTopTab(BaseTabAdapter adapter) {
        adapter.addTopTab(AllFragment.class, getTitle(0), null);
        adapter.addTopTab(AndroidFragment.class, getTitle(1), null);
        adapter.addTopTab(IOSFragment.class, getTitle(2), null);
        adapter.addTopTab(AppFragment.class, getTitle(3), null);
        adapter.addTopTab(ResourceFragment.class, getTitle(4), null);
        adapter.addTopTab(WebFragment.class, getTitle(5), null);
        adapter.addTopTab(TuiJianFragment.class, getTitle(6), null);
       return getResources().getStringArray(R.array.ganhuo_indicator_title).length;
    }


    /**
     * 获取标题
     *
     * @param position
     * @return
     */
    private String getTitle(int position) {
        return getResources().getStringArray(R.array.ganhuo_indicator_title)[position];
    }
}
