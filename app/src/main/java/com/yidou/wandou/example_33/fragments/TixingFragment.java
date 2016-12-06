package com.yidou.wandou.example_33.fragments;

import android.os.Bundle;
import android.view.View;

import com.yidou.wandou.example_33.base.BaseFragment;
import com.yidou.wandou.example_33.ui.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/12/2.
 */

public class TixingFragment extends BaseFragment
{

    private Unbinder mUnbinder;

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_tixing;
    }

    @Override
    public void initVariables()
    {

    }

    @Override
    public void initViews(View view, Bundle savedInstanceState)
    {
        mUnbinder = ButterKnife.bind(this, view);
    }


    @Override
    public void initLoadData()
    {

    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
