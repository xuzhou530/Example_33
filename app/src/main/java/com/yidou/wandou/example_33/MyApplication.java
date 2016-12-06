package com.yidou.wandou.example_33;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2016/12/5.
 */

public class MyApplication extends Application
{
    public static MyApplication application;
    public static MyApplication getContext()
    {
        return application;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        application = this;
        Fresco.initialize(this);
    }
}
