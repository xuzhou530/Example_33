package com.yidou.wandou.example_33.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.yidou.wandou.example_33.Config;
import com.yidou.wandou.example_33.base.BasePresneterImpl;
import com.yidou.wandou.example_33.constract.INews;
import com.yidou.wandou.example_33.model.Datas;
import com.yidou.wandou.example_33.utils.ApiManage;
import com.yidou.wandou.example_33.utils.CacheUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/5.
 */

public class NewsPresenterImpl extends BasePresneterImpl implements INews.presenter
{
    private INews.Views mViews;
    private Gson mGson;
    private CacheUtil mUtil;

    public NewsPresenterImpl(Context context, INews.Views views)
    {
        mViews = views;
        mUtil = CacheUtil.get(context);
        mGson = new Gson();
    }

    @Override
    public void loadingDatasFromNet()
    {
        Subscription subscription = ApiManage.getInstances().getNewService()
                .getNewsService()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Datas>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        mViews.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Datas datas)
                    {
                        mViews.showInfo(datas.getResult().getData());
                        mUtil.put(Config.NEWS, mGson.toJson(datas));
                    }
                });
        addSubcrible(subscription);
    }

    @Override
    public void loadingDatasFromCache()
    {
        if (mUtil.getAsJSONObject(Config.NEWS) != null)
        {
            Datas datas = mGson.fromJson(mUtil.getAsJSONObject(Config.NEWS).toString(), Datas.class);
            mViews.showInfo(datas.getResult().getData());
        }
    }
}
