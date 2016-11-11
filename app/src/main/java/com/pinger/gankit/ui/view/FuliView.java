package com.pinger.gankit.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pinger.gankit.R;
import com.pinger.gankit.base.RootView;
import com.pinger.gankit.model.bean.GankBean;
import com.pinger.gankit.presenter.contact.FuliContact;

import java.util.List;

import butterknife.BindView;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.view
 *  @文件名:   FuliView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/12 0:05
 *  @描述：    TODO
 */

public class FuliView extends RootView implements FuliContact.View {

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;

    public FuliView(Context context) {
        this(context, null);
    }

    public FuliView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.view_fuli, this);
    }

    @Override
    protected void initView() {
        mTvName.setText(mContext.getString(R.string.fuli));
        mIvIcon.setImageDrawable(new IconicsDrawable(mContext).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_gif).sizeDp(20));




    }

    @Override
    protected void initEvent() {

    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void showContent(List<GankBean> gankBeanList) {

    }

    @Override
    public void showMoreContent(List<GankBean> gankBeanMoreList) {

    }

    @Override
    public void setPresenter(FuliContact.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshFailed(String msg) {

    }

    @Override
    public void loadMoreFailed(String msg) {

    }
}
