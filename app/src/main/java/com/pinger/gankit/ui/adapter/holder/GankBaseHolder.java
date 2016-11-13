package com.pinger.gankit.ui.adapter.holder;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pinger.gankit.R;
import com.pinger.gankit.model.bean.GankBean;
import com.pinger.gankit.utils.ThemeUtil;

/*
 *  @项目名：  GankIT
 *  @包名：    com.pinger.gankit.ui.adapter.holder
 *  @文件名:   GankBaseHolder
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/13 9:58
 *  @描述：    使用Holder重构干货适配器
 */
public class GankBaseHolder extends BaseViewHolder<GankBean> {

    private final Context mContext;
    private TextView mTitle;

    public GankBaseHolder(ViewGroup parent) {
        super(parent, R.layout.item_gank);

        mContext = parent.getContext();
        mTitle = $(R.id.tv_title);
    }

    @Override
    public void setData(GankBean gankBean) {
        // 设置条目图片
        switch (gankBean.getType()) {
            case "Android":
                ThemeUtil.setIconDrawable(mContext, mTitle, MaterialDesignIconic.Icon.gmi_android);
                break;
            case "iOS":
                ThemeUtil.setIconDrawable(mContext, mTitle, MaterialDesignIconic.Icon.gmi_apple);
                break;
            case "前端":
                ThemeUtil.setIconDrawable(mContext, mTitle, MaterialDesignIconic.Icon.gmi_language_javascript);
                break;
            case "拓展资源":
                ThemeUtil.setIconDrawable(mContext, mTitle, FontAwesome.Icon.faw_location_arrow);
                break;
            case "App":
                ThemeUtil.setIconDrawable(mContext, mTitle, MaterialDesignIconic.Icon.gmi_apps);
                break;
            case "瞎推荐":
                ThemeUtil.setIconDrawable(mContext, mTitle, MaterialDesignIconic.Icon.gmi_more);
                break;

        }

        // 设置文字链接
        mTitle.setText(Html.fromHtml("<a href=\""
                + gankBean.getUrl() + "\">"
                + gankBean.getDesc() + "</a>"
                + "[" + gankBean.getWho() + "]"));

        // 设置链接跳转
        mTitle.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
