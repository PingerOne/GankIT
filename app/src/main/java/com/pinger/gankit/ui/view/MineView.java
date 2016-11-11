package com.pinger.gankit.ui.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pinger.gankit.R;
import com.pinger.gankit.app.Constant;
import com.pinger.gankit.base.RootView;
import com.pinger.gankit.manager.ImageManager;
import com.pinger.gankit.model.bean.UserBean;
import com.pinger.gankit.model.bean.VideoType;
import com.pinger.gankit.presenter.contact.MineContact;
import com.pinger.gankit.ui.activity.MainActivity;
import com.pinger.gankit.utils.SPUtil;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.view
 *  @文件名:   MineView
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/5 22:34
 *  @描述：    我的模块的View层
 */

public class MineView extends RootView<MineContact.Presenter> implements MineContact.View, View.OnClickListener {


    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.head_layout)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_user_avatar)
    CircleImageView mIvUserAvatar;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    private static final String mAppid = "222222";
    private Tencent mTencent;
    private MainActivity mActivity;
    private String mNickname;

    public MineView(Context context) {
        this(context, null);
    }

    public MineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setPresenter(MineContact.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.view_mine, this);
    }

    @Override
    protected void initView() {
        mActivity = (MainActivity) mContext;
        initTentcentSDK();
        mActivity.setSupportActionBar(mToolbar);

        mActivity.getSupportActionBar().setIcon(R.drawable.ic_account_box_white_24dp);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -mRelativeLayout.getHeight() / 2) {
                    if (mNickname != null) {
                        mCollapsingToolbarLayout.setTitle(mNickname);
                    } else {
                        mCollapsingToolbarLayout.setTitle("GankIT");
                    }
                } else {
                    mCollapsingToolbarLayout.setTitle(" ");
                }
            }
        });
        EventBus.getDefault().register(this);

        if (!SPUtil.getBoolean(mActivity, Constant.IS_LOGIN, false)) {
            mIvUserAvatar.setImageResource(R.mipmap.user_unknow);
            mTvUserName.setText("请先登录");
            return;
        }

        mTvUserName.setText(SPUtil.getString(mActivity, Constant.USER_NICK_NAME));
        ImageManager.load(mActivity, SPUtil.getString(mActivity, Constant.USER_AVATER), mIvUserAvatar);
    }

    /**
     * 初始化SDK
     */
    private void initTentcentSDK() {
        mTencent = Tencent.createInstance(mAppid, mActivity);
    }

    @Override
    protected void initEvent() {
        mIvUserAvatar.setOnClickListener(this);
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void showContent(List<VideoType> types) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_avatar:
                if (SPUtil.getBoolean(mActivity, Constant.IS_LOGIN, false)) {
                    Toast.makeText(mActivity, "您已经登录了", Toast.LENGTH_SHORT).show();
                } else {
                    // 跳转到QQ登录
                    mTencent.login(mActivity, "all", mActivity);
                }
                break;
        }
    }

    @Subscriber(tag = MainActivity.LOGIND_RESULT)
    public void getLoginResult(Object result) {
        JSONObject jsonObject = (JSONObject) result;
        System.out.println(result.toString());
        //授权成功，获取用户信息
        String token = jsonObject.optString(Constants.PARAM_ACCESS_TOKEN);
        String expires = jsonObject.optString(Constants.PARAM_EXPIRES_IN);
        String openId = jsonObject.optString(Constants.PARAM_OPEN_ID);
        if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                && !TextUtils.isEmpty(openId)) {
            mTencent.setAccessToken(token, expires);
            mTencent.setOpenId(openId);
        }
        UserInfo userInfo = new UserInfo(mActivity, mTencent.getQQToken());
        userInfo.getUserInfo(new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                putUserInfo("", "", false);
            }

            @Override
            public void onComplete(Object response) {
                Gson gson = new Gson();
                UserBean info = gson.fromJson(response.toString(), UserBean.class);
                ImageManager.load(mActivity, info.figureurl_qq_2, mIvUserAvatar);
                mNickname = info.nickname;
                mTvUserName.setText(mNickname);

                // 由于没有服务器，暂时将昵称和头像链接保存在本地
                putUserInfo(info.nickname, info.figureurl_qq_2, true);
            }

            @Override
            public void onCancel() {
                putUserInfo("", "", false);
            }
        });
    }

    private void putUserInfo(String name, String imgUrl, boolean isLogin) {
        SPUtil.putString(mActivity, Constant.USER_NICK_NAME, name);
        SPUtil.putString(mActivity, Constant.USER_AVATER, imgUrl);
        SPUtil.putBoolean(mActivity, Constant.IS_LOGIN, isLogin);
    }
}
