package com.pinger.gankit.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.pinger.gankit.R;
import com.pinger.gankit.base.SwipeBackActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.activity
 *  @文件名:   PhotoActivity
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/12 23:13
 *  @描述：    TODO
 */

public class PhotoActivity extends SwipeBackActivity {

    public static final String IMAGE_TITLE = "image_title";
    public static final String IMAGE_URL = "image_url";
    @BindView(R.id.picture)
    ImageView mPicture;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private String mImageTitle,mImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        unbinder = ButterKnife.bind(this);

        parseIntent();
    }

    private void parseIntent() {
        mImageTitle = getIntent().getStringExtra(IMAGE_TITLE);
        mImageUrl = getIntent().getStringExtra(IMAGE_URL);
    }
}
