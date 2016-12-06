package com.yidou.wandou.example_33.utils;

import com.yidou.wandou.example_33.Config;
import com.yidou.wandou.example_33.MyApplication;
import com.yidou.wandou.example_33.services.NewsServices;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/12/1.
 */

public class ApiManage
{

//    public static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor()
//    {
//        @Override
//        public Response intercept(Chain chain) throws IOException
//        {
//            Response originalResponse = chain.proceed(chain.request());
//            if (NetWorkUtil.isNetWorkAvailable(MyApplication.getContext()))
//            {
//                int maxAge = 60; // 在线缓存在1分钟内可读取
//                return originalResponse.newBuilder()
//                        .removeHeader("Pragma")
//                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", "public, max-age=" + maxAge)
//                        .build();
//            } else
//            {
//                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
//                return originalResponse.newBuilder()
//                        .removeHeader("Pragma")
//                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                        .build();
//            }
//        }
//    };
//
    public static ApiManage apiManage;
//    private static File httpCacheDirectory = new File(MyApplication.getContext().getCacheDir(), "newscache");
//    private static int cachesize = 10 * 1024 * 1024;
//    private static Cache cache = new Cache(httpCacheDirectory, cachesize);
//
//    private static OkHttpClient client = new OkHttpClient.Builder()
//            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
//            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
//            .cache(cache)
//            .build();


    public NewsServices mService;
    private Object mObject = new Object();

    //单例模式
    public static ApiManage getInstances()
    {
        if (apiManage == null)
        {
            synchronized (ApiManage.class)
            {
                if (apiManage == null)
                {
                    apiManage = new ApiManage();
                }
            }
        }
        return apiManage;
    }

    //首页热门文章主体消息
    public NewsServices getNewService()
    {
        if (mService == null)
        {
            synchronized (mObject)
            {
                if (mService == null)
                {
                    mService = new Retrofit.Builder()
                            .baseUrl(Config.HOME_NEWS_URL)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(NewsServices.class);
                }
            }
        }
        return mService;
    }


}
