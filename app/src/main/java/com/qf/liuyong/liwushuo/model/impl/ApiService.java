package com.qf.liuyong.liwushuo.model.impl;

import com.qf.liuyong.liwushuo.model.bean.MainBean;
import com.qf.liuyong.liwushuo.model.bean.SelectBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/5.
 */
public interface ApiService {

    @GET("v2/channels/preset?gender=1&generation=2")
    Observable<MainBean> querySelectBean();

    @GET("v2/channels/{id}/items?ad=2&gender=1&generation=2&limit=20")
    Observable<SelectBean> querySelectedBean(@Path("id") int id,@Query("offset") int offset);

}
