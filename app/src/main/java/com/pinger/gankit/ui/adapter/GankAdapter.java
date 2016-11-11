package com.pinger.gankit.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pinger.gankit.R;
import com.pinger.gankit.model.bean.GankBean;

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
        baseViewHolder.setText(R.id.tv_desc, gankBean.getDesc());
    }
}
