package com.qf.liuyong.liwushuo.dagger;

import com.qf.liuyong.liwushuo.ui.MainActivity;
import com.qf.liuyong.liwushuo.ui.fragment.BaseFragment;
import com.qf.liuyong.liwushuo.ui.fragment.SelectFragment;

import dagger.Component;

/**
 * Created by Administrator on 2016/11/5.
 */
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainActivity activity);

    void inject(SelectFragment selectFragment);

    void inject(BaseFragment baseFragment);
}
