package com.pinger.gankit.ui.fragment.news;

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.news.NewsGamePresenter;
import com.pinger.gankit.ui.view.news.GameView;

import butterknife.BindView;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.fragment.news
 *  @文件名:   GameFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/13 18:49
 *  @描述：    TODO
 */

public class GameFragment extends BaseFragment {
    @BindView(R.id.gameView)
    GameView mGameView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_game;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new NewsGamePresenter(mGameView);
    }

    @Override
    protected void loadData() {
        ((NewsGamePresenter)mPresenter).onRefresh();
    }
}
