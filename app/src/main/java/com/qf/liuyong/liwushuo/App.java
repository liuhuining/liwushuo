package com.qf.liuyong.liwushuo;

import android.app.Application;

/**
 * Created by Administrator on 2016/11/5.
 */
public class App extends Application {
    private static App instance;
    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
    }
}
