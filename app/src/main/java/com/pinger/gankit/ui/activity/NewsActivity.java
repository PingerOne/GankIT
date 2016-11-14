package com.pinger.gankit.ui.activity;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseTabActivity;
import com.pinger.gankit.ui.adapter.BaseTabAdapter;
import com.pinger.gankit.ui.fragment.news.AmuseFragment;
import com.pinger.gankit.ui.fragment.news.GameFragment;
import com.pinger.gankit.ui.fragment.news.MilitaryFragment;
import com.pinger.gankit.ui.fragment.news.NewsTopFragment;
import com.pinger.gankit.ui.fragment.news.ScienceFragment;
import com.pinger.gankit.ui.fragment.news.SocietyFragment;
import com.pinger.gankit.ui.fragment.news.SportsFragment;

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
        ivIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_book).sizeDp(20));
    }

    @Override
    protected int setupWithTopTab(BaseTabAdapter adapter) {
        adapter.addTopTab(NewsTopFragment.class, getTitle(0), null);
        adapter.addTopTab(SocietyFragment.class, getTitle(1), null);
        adapter.addTopTab(AmuseFragment.class, getTitle(2), null);
        adapter.addTopTab(ScienceFragment.class, getTitle(3), null);
        adapter.addTopTab(GameFragment.class, getTitle(4), null);
        adapter.addTopTab(MilitaryFragment.class, getTitle(5), null);
        adapter.addTopTab(SportsFragment.class, getTitle(6), null);
        return getResources().getStringArray(R.array.news_title).length;
    }

    /**
     * 获取标题
     *
     * @param position
     * @return
     */
    private String getTitle(int position) {
        return getResources().getStringArray(R.array.news_title)[position];
    }
}
