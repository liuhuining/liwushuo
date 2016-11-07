package com.qf.liuyong.liwushuo.view;

import com.qf.liuyong.liwushuo.model.bean.SelectBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */
public interface IBaseView {
    void setListData(List<SelectBean.DataBean.ItemsBean> itemsBean);
}
