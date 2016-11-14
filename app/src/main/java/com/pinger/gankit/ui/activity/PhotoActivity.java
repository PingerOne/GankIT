package com.pinger.gankit.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pinger.gankit.R;
import com.pinger.gankit.base.SwipeBackActivity;
import com.pinger.gankit.manager.ImageManager;
import com.pinger.gankit.model.net.RxPhoto;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


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
    private String mImageTitle, mImageUrl;
    private boolean isShow = false;
    private int mAppBarHeight;
    private PhotoViewAttacher mPhotoViewAttacher;

    @BindView(R.id.picture)@Nullable PhotoView mPicture;
    @BindView(R.id.toolbar) @Nullable Toolbar mToolbar;
    @BindView(R.id.root) @Nullable View mRoot;
    @BindView(R.id.appbar) @Nullable AppBarLayout mAppbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        unbinder = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        parseIntent();
        initToolBar(mToolbar, true, mImageTitle);

        // 揭露式动画
        ViewCompat.setTransitionName(mPicture, TRANSIT_PIC);
        // 填充图片
        ImageManager.getsInstance().load(this, mImageUrl, mPicture);

        // 集成PhotoView实现图片缩放
        setupPhotoAttacher();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mAppBarHeight = mAppbar.getHeight();
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
                save();
                break;
            case R.id.action_share:
                // 分享
                shareImage();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 保存到本地
     */
    private void save() {
        Subscription s = RxPhoto.saveImageAndGetPathObservable(this, mImageUrl, mImageTitle)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(uri -> {
                    File appDir = new File(Environment.getExternalStorageDirectory(), "gankit");
                    String msg = String.format(getString(R.string.picture_has_save_to),
                            appDir.getAbsolutePath());
                    Snackbar.make(mRoot, msg, Snackbar.LENGTH_SHORT).show();
                }, error -> Snackbar.make(mRoot, error.getMessage() + "\n再试试...", Snackbar.LENGTH_SHORT).show());
        addSubscribe(s);
    }


    /**
     * 分享图片
     */
    private void shareImage() {
        RxPhoto.saveImageAndGetPathObservable(this, mImageUrl, mImageTitle)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(uri -> share(uri),
                        error -> Snackbar.make(mRoot, error.getMessage(), Snackbar.LENGTH_SHORT).show());
    }


    private void share(Uri uri) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)));
    }


    private void setupPhotoAttacher() {
        mPhotoViewAttacher = new PhotoViewAttacher(mPicture);
        mPhotoViewAttacher.setOnViewTapListener((view, v, v1) -> hideOrShowToolbar());
        mPhotoViewAttacher.setOnLongClickListener(v -> {
            // 弹出对话框
            new MaterialDialog.Builder(PhotoActivity.this)
                    .title(getString(R.string.save_to_local))
                    .positiveText(getString(R.string.confirm))
                    .onPositive((dialog, which) -> {
                        save();
                    })
                    .negativeText(getString(R.string.cancel))
                    .show();

            return true;
        });
    }

    /**
     * 显示隐藏ToolBar
     */
    private void hideOrShowToolbar() {
        mAppbar.animate()
                .translationY(isShow ? 0 : -mAppBarHeight)
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        isShow = !isShow;
    }
}
