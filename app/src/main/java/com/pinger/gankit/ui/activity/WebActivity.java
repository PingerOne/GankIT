package com.pinger.gankit.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

    @BindView(R.id.webView) @Nullable WebView mWebView;
    @BindView(R.id.progress_nar) @Nullable ProgressBar mProgressBar;
    @BindView(R.id.iv_back) @Nullable ImageView mIvBack;
    @BindView(R.id.tv_title) @Nullable TextView mTvTitle;
    @BindView(R.id.iv_share) @Nullable ImageView mIvShare;
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
        mTvTitle.setText(mWebTitle);
        mIvBack.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_arrow_left).sizeDp(16));
        mIvShare.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_share).sizeDp(16));
        mIvBack.setOnClickListener(view -> onBackPressed());
        mIvShare.setOnClickListener(view -> {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, mWebUrl);
            shareIntent.setType("text/plain");

            //设置分享列表的标题，并且每次都显示分享列表
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to_no)));
        });
    }

    private void initWebView() {
        mProgressBar.setMax(100);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (mProgressBar == null) {
                    return;
                }

                mProgressBar.setProgress(newProgress);
                if (newProgress >= 100) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl(mWebUrl);
    }
}

