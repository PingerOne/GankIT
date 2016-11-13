package com.pinger.gankit.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pinger.gankit.R;
import com.pinger.gankit.base.SwipeBackActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.activity
 *  @文件名:   WebActivity
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/13 11:53
 *  @描述：    TODO
 */

public class WebActivity extends SwipeBackActivity {

    public static final String WEB_TITLE = "web_title";
    public static final String WEB_URL = "web_url";
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.webView)
    WebView mWebView;
    private String mWebTitle;
    private String mWebUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        unbinder = ButterKnife.bind(this);

        parseIntent();
        initTitle();
        initWebView();
    }

    /**
     * 解析Intent数据
     */
    private void parseIntent() {
        mWebTitle = getIntent().getStringExtra(WEB_TITLE);
        mWebUrl = getIntent().getStringExtra(WEB_URL);
    }


    private void initTitle() {
        mTvName.setText(mWebTitle);
        mIvIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_arrow_back).sizeDp(20));
        mIvIcon.setOnClickListener(view -> onBackPressed());
    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl(mWebUrl);
    }
}
