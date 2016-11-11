package com.pinger.gankit.presenter;

import android.support.test.espresso.core.deps.guava.base.Preconditions;

import com.pinger.gankit.base.RxPresenter;
import com.pinger.gankit.manager.RequestManager;
import com.pinger.gankit.model.bean.VideoRes;
import com.pinger.gankit.model.net.VideoHttpResponse;
import com.pinger.gankit.presenter.contact.SubjectContact;
import com.pinger.gankit.ui.view.SubjectView;
import com.pinger.gankit.utils.RxUtil;
import com.pinger.gankit.utils.StringUtil;

import rx.Subscription;
import rx.functions.Action1;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter
 *  @文件名:   SubjectPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/5 20:05
 *  @描述：    主题的Presenter层
 */

public class SubjectPresenter extends RxPresenter implements SubjectContact.Presenter {

    private final SubjectView mSubjectView;

    private int page;

    public SubjectPresenter(SubjectView subjectView) {
        mSubjectView = Preconditions.checkNotNull(subjectView);
        // 关联
        mSubjectView.setPresenter(this);
    }

    @Override
    public void onRefresh() {
        page = 0;
        requestData();
    }

    /**
     * 请求数据
     */
    private void requestData() {
        Subscription rxSubscription = RequestManager.getVideoApi().getHomePage()
                .compose(RxUtil.<VideoHttpResponse<VideoRes>>rxSchedulerHelper())
                .compose(RxUtil.<VideoRes>handleResult())
                .subscribe(new Action1<VideoRes>() {
                    @Override
                    public void call(final VideoRes res) {
                        if (res != null) {
                            if (mSubjectView.isActive()) {
                                mSubjectView.showContent(res);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mSubjectView.refreshFail(StringUtil.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }
}
