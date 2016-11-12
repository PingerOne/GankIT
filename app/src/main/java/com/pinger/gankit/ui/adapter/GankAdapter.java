package com.pinger.gankit.ui.adapter;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pinger.gankit.R;
import com.pinger.gankit.model.bean.GankBean;
import com.pinger.gankit.utils.ThemeUtil;

import java.util.List;

/*
 *  @项目名：  GankIT
 *  @包名：    com.pinger.gankit.ui.adapter
 *  @文件名:   GankAdapter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 10:34
 *  @描述：    抽取的干货适配器，一个适配器完成所有的条目显示
 */
public class GankAdapter extends BaseQuickAdapter<GankBean> {
    public GankAdapter(List<GankBean> data) {
        super(R.layout.item_gank, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GankBean gankBean) {
        TextView tvTitle = baseViewHolder.getView(R.id.tv_title);

        // 设置条目图片
        switch (gankBean.getType()) {
            case "Android":
                ThemeUtil.setIconDrawable(mContext, tvTitle, MaterialDesignIconic.Icon.gmi_android);
                break;
            case "iOS":
                ThemeUtil.setIconDrawable(mContext, tvTitle, MaterialDesignIconic.Icon.gmi_apple);
                break;
            case "前端":
                ThemeUtil.setIconDrawable(mContext, tvTitle, MaterialDesignIconic.Icon.gmi_language_javascript);
                break;
            case "拓展资源":
                ThemeUtil.setIconDrawable(mContext, tvTitle, FontAwesome.Icon.faw_location_arrow);
                break;
            case "App":
                ThemeUtil.setIconDrawable(mContext, tvTitle, MaterialDesignIconic.Icon.gmi_apps);
                break;
            case "瞎推荐":
                ThemeUtil.setIconDrawable(mContext, tvTitle, MaterialDesignIconic.Icon.gmi_more);
                break;

        }


        // 设置文字链接
        tvTitle.setText(Html.fromHtml("<a href=\""
                + gankBean.getUrl() + "\">"
                + gankBean.getDesc() + "</a>"
                + "[" + gankBean.getWho() + "]"));

        // 设置链接跳转
        tvTitle.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
