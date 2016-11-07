package com.qf.liuyong.liwushuo.presenter;

import com.qf.liuyong.liwushuo.view.ISelectView;

/**
 * Created by Administrator on 2016/11/6.
 */
public interface ISelectPresenter {

    void setView(ISelectView selectView);

    void querySelectedBean(int id,int offset);
}
