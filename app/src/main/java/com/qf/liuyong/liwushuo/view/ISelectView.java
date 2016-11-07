package com.qf.liuyong.liwushuo.view;

import com.qf.liuyong.liwushuo.model.bean.SelectBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/6.
 */
public interface ISelectView {
    void refreshAdapter(List<String> groupList, Map<String,List<SelectBean.DataBean.ItemsBean>> map);
}
