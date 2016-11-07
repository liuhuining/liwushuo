package com.qf.liuyong.liwushuo.presenter.impl;

import com.qf.liuyong.liwushuo.model.bean.MainBean;
import com.qf.liuyong.liwushuo.model.impl.ApiService;
import com.qf.liuyong.liwushuo.presenter.IMainPresenter;
import com.qf.liuyong.liwushuo.view.IMainView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/5.
 */
public class MainPresenter implements IMainPresenter {

    private ApiService apiService;
    private IMainView mainView;

    public MainPresenter(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void setView(IMainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void queryMainBean() {
        final List<String> titles = new ArrayList<>();
        final List<Integer> ids = new ArrayList<>();
        apiService.querySelectBean()
                .flatMap(new Func1<MainBean, Observable<MainBean.DataBean.ChannelsBean>>() {
                    @Override
                    public Observable<MainBean.DataBean.ChannelsBean> call(MainBean mainBean) {
                        List<MainBean.DataBean.ChannelsBean> items = mainBean.getData().getChannels();
                        return Observable.from(items);
                    }
                })
                .map(new Func1<MainBean.DataBean.ChannelsBean, List<String>>() {
                    @Override
                    public List<String> call(MainBean.DataBean.ChannelsBean channelsBean) {
                        String name = channelsBean.getName();
                        int id = channelsBean.getId();
                        titles.add(name);
                        ids.add(id);
                        return titles;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {
                        mainView.refreshAdapter(titles,ids);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String> list) {

                    }
                });
    }
}
