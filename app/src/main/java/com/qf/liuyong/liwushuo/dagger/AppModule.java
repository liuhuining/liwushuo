package com.qf.liuyong.liwushuo.dagger;

import com.qf.liuyong.liwushuo.model.impl.ApiService;
import com.qf.liuyong.liwushuo.presenter.IBasePresenter;
import com.qf.liuyong.liwushuo.presenter.IMainPresenter;
import com.qf.liuyong.liwushuo.presenter.ISelectPresenter;
import com.qf.liuyong.liwushuo.presenter.impl.BasePresenter;
import com.qf.liuyong.liwushuo.presenter.impl.MainPresenter;
import com.qf.liuyong.liwushuo.presenter.impl.SeclectPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/5.
 */
@Module
public class AppModule {

    @Provides
    public ApiService provideRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

    @Provides
    public IMainPresenter provideMainPresenter(ApiService apiService){
        return new MainPresenter(apiService);
    }

    @Provides
    public ISelectPresenter provideSelectPresenter(ApiService apiService){
        return  new SeclectPresenter(apiService);
    }

    @Provides
    public IBasePresenter provideBasePresenter(ApiService apiService){
        return new BasePresenter(apiService);
    }

}
