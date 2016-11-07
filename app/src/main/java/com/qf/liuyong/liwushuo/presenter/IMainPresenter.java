package com.qf.liuyong.liwushuo.presenter;

import com.qf.liuyong.liwushuo.view.IMainView;

/**
 * Created by Administrator on 2016/11/5.
 */
public interface IMainPresenter {

    void setView(IMainView mainView);
    void queryMainBean();
}
