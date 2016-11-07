package com.qf.liuyong.liwushuo.presenter.impl;

import android.support.v4.util.ArrayMap;

import com.qf.liuyong.liwushuo.model.bean.SelectBean;
import com.qf.liuyong.liwushuo.model.impl.ApiService;
import com.qf.liuyong.liwushuo.presenter.ISelectPresenter;
import com.qf.liuyong.liwushuo.tool.Utils;
import com.qf.liuyong.liwushuo.view.ISelectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/6.
 */
public class SeclectPresenter implements ISelectPresenter {

    private ApiService apiService;
    private ISelectView selectView;

    public SeclectPresenter(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void setView(ISelectView selectView) {
        this.selectView = selectView;
    }

    @Override
    public void querySelectedBean(int id,int offset) {
        final Map<String,List<SelectBean.DataBean.ItemsBean>> map = new ArrayMap<>();
        final List<String> dataList = new ArrayList<>();
        apiService.querySelectedBean(id,offset)
                .flatMap(new Func1<SelectBean, Observable<SelectBean.DataBean.ItemsBean>>() {
                    @Override
                    public Observable<SelectBean.DataBean.ItemsBean> call(SelectBean selectBean) {
                        List<SelectBean.DataBean.ItemsBean> items = selectBean.getData().getItems();
                        return Observable.from(items);
                    }
                })
                .map(new Func1<SelectBean.DataBean.ItemsBean, Map<String,List<SelectBean.DataBean.ItemsBean>>>() {
                    @Override
                    public Map<String, List<SelectBean.DataBean.ItemsBean>> call(SelectBean.DataBean.ItemsBean itemsBean) {
                        int published_at = itemsBean.getPublished_at();
                        String formatDate = Utils.formatDate(published_at * 1000);
                        List<SelectBean.DataBean.ItemsBean> items = map.get(formatDate);
                        if (items == null){
                            items = new ArrayList<SelectBean.DataBean.ItemsBean>();
                            dataList.add(formatDate);
                            map.put(formatDate,items);
                        }
                        items.add(itemsBean);
                        return map;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Map<String, List<SelectBean.DataBean.ItemsBean>>>() {
                    @Override
                    public void onCompleted() {
                        selectView.refreshAdapter(dataList,map);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Map<String, List<SelectBean.DataBean.ItemsBean>> map) {

                    }
                });
    }

}
