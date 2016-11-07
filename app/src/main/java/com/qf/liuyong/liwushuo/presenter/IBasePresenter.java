package com.qf.liuyong.liwushuo.presenter;

import com.qf.liuyong.liwushuo.view.IBaseView;

/**
 * Created by Administrator on 2016/11/6.
 */
public interface IBasePresenter {
    void setBaseView(IBaseView baseView);
    void querySelectedList(int id,int offset);
}
