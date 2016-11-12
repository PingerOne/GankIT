package com.pinger.gankit.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.pinger.gankit.R;
import com.pinger.gankit.base.SwipeBackActivity;
import com.pinger.gankit.manager.ImageManager;

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
    private static final String TRANSIT_PIC = "pic";
    @BindView(R.id.picture)
    ImageView mPicture;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    private String mImageTitle, mImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        unbinder = ButterKnife.bind(this);

        parseIntent();

        initToolBar(mToolbar, true, mImageTitle);

        // 揭露式动画
        ViewCompat.setTransitionName(mPicture, TRANSIT_PIC);
        // 填充图片
        ImageManager.load(this, mImageUrl, mPicture);
        // 设置图片适配器，加载左右多张图片
    }

    private void parseIntent() {
        mImageTitle = getIntent().getStringExtra(IMAGE_TITLE);
        mImageUrl = getIntent().getStringExtra(IMAGE_URL);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pictrue, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                // 保存到本地
                break;
            case R.id.action_share:
                // 分享
                break;
            case android.R.id.home:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
