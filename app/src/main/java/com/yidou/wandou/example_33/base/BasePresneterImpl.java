package com.yidou.wandou.example_33.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/12/5.
 */

public class BasePresneterImpl implements BasePrensenter
{
    private CompositeSubscription mCompositeSubscription;

    protected void addSubcrible(Subscription s)
    {
        if (mCompositeSubscription == null)
        {
            mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    public void unSubcrible()
    {
        if (mCompositeSubscription != null)
        {
            this. mCompositeSubscription.unsubscribe();
        }
    }
}
