package com.pinger.gankit.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pinger.gankit.R;
import com.pinger.gankit.base.SwipeBackActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends SwipeBackActivity {

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.tv_jianshu)
    TextView mTvJianshu;
    @BindView(R.id.tv_github)
    TextView mTvGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        unbinder = ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        mIvIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_arrow_back).sizeDp(20));
//        mCollapsingToolbarLayout.settext
        mIvIcon.setOnClickListener(view -> onBackPressed());
        mCollapsingToolbarLayout.setTitle(getString(R.string.setting));
        initAbout();
    }

    private void initAbout() {
        // 设置文字链接
        mTvJianshu.setText(Html.fromHtml("<a href=\""
                + getString(R.string.root_jianshu) + "\">"
                + getString(R.string.dasheng) + "</a>"));
        mTvGithub.setText(Html.fromHtml("<a href=\""
                + getString(R.string.root_github) + "\">"
                + getString(R.string.pinger) + "</a>"));
        mTvJianshu.setMovementMethod(LinkMovementMethod.getInstance());
        mTvGithub.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
