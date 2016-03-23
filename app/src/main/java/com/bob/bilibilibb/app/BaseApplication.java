package com.bob.bilibilibb.app;

import android.app.Application;
import android.os.Handler;

/**
 * Created by Administrator on 2016/3/22.
 */
public class BaseApplication extends Application{

    private static BaseApplication instance;
    private static Handler mHandler; //主线程Handler

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mHandler = new Handler();
    }

    public static BaseApplication getInstance(){
        return instance;
    }

    public static Handler getMainHandler(){
        return mHandler;
    }

}
