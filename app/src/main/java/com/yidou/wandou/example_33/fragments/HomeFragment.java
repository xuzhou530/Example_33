package com.yidou.wandou.example_33.fragments;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.yidou.wandou.example_33.Config;
import com.yidou.wandou.example_33.adapter.TopAdapter;
import com.yidou.wandou.example_33.base.BaseFragment;
import com.yidou.wandou.example_33.constract.INews;
import com.yidou.wandou.example_33.model.Banners;
import com.yidou.wandou.example_33.model.Datas;
import com.yidou.wandou.example_33.presenter.NewsPresenterImpl;
import com.yidou.wandou.example_33.ui.DetailActivity;
import com.yidou.wandou.example_33.ui.R;
import com.zanlabs.widget.infiniteviewpager.InfiniteViewPager;
import com.zanlabs.widget.infiniteviewpager.indicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/12/2.
 */

public class HomeFragment extends BaseFragment implements INews.Views
{

    @BindView(R.id.home_recycler)
    RecyclerView mHomeRecycler;
    @BindView(R.id.home_viewpager)
    InfiniteViewPager mHomeViewpager;
    @BindView(R.id.home_indicator)
    CirclePageIndicator mHomeIndicator;
    @BindView(R.id.home_header)
    RecyclerViewHeader mHomeHeader;
    @BindView(R.id.home_refresh)
    SwipeRefreshLayout mHomeRefresh;


    private Unbinder mUnbinder;
    private BaseRecyclerAdapter<Datas.ResultBean.DataBean> mAdapter;
    private NewsPresenterImpl mPresenter;
//    private List<Banners> mList;

    private List<String> banner_img;
    private List<String> banner_url;

    private boolean connect = false;//判断网络是否连接正常
    private Handler mHandler;



    @Override
    public int getLayoutId()
    {
        checkNet();
        return R.layout.fragment_home;
    }

    @Override
    public void initVariables()
    {

        mPresenter = new NewsPresenterImpl(getActivity(), this);
//        mList = new ArrayList<>();
        banner_img = new ArrayList<>();
        banner_url = new ArrayList<>();
        banner_img = Arrays.asList(Config.BANNER_IMGS);
        banner_url = Arrays.asList(Config.BANNER_URL);
        mHandler = new Handler();
    }

    @Override
    public void initViews(View view, Bundle savedInstanceState)
    {
        mUnbinder = ButterKnife.bind(this, view);
        mHomeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new BaseRecyclerAdapter<Datas.ResultBean.DataBean>(getActivity(), null, R.layout.item)
        {
            @Override
            protected void convert(BaseViewHolder helper, final Datas.ResultBean.DataBean item)
            {
                helper.setText(R.id.author_name, item.getAuthor_name());
                String title = item.getTitle();
                if (title.length() > 15)
                {
                    String s = title.substring(0, 15) + "...";
                    helper.setText(R.id.text_title, s);
                }else
                {
                    helper.setText(R.id.text_title, title);
                }
                helper.setText(R.id.realtype, item.getRealtype());
                helper.setText(R.id.date, item.getDate());
                SimpleDraweeView imageViews = (SimpleDraweeView) helper.getConvertView().findViewById(R.id.thumbnail);
                imageViews.setImageURI(item.getThumbnail_pic_s());
                RelativeLayout layout = (RelativeLayout) helper.getConvertView().findViewById(R.id.home_item);
                layout.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent it = new Intent(getActivity(), DetailActivity.class);
                        it.putExtra(Config.NEWS, item.getUrl());
                        getActivity().startActivity(it);
                    }
                });
            }
        };
        mHomeRecycler.setAdapter(mAdapter);

        mHomeRefresh.setColorSchemeResources(R.color.colorBG);
        mHomeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                mHomeRefresh.setRefreshing(true);
                initLoadData();
                mHandler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mHomeRefresh.setRefreshing(false);
                    }
                }, 1200);
            }
        });
        //广告窗体
        mHomeHeader.attachTo(mHomeRecycler,true);
        TopAdapter adapter = new TopAdapter(getActivity(), banner_img,banner_url);
        mHomeViewpager.setAdapter(adapter);
        mHomeViewpager.setAutoScrollTime(3000);
        mHomeViewpager.startAutoScroll();
        mHomeIndicator.setViewPager(mHomeViewpager);
    }

    public void checkNet()//网络是否已经连接上
    {
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        connect = info != null && info.isConnected();
    }




    @Override
    public void initLoadData()
    {
        if (connect)
        {
            mPresenter.loadingDatasFromNet();
        }else
        {
            mPresenter.loadingDatasFromCache();
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mUnbinder.unbind();
    }


    @Override
    public void showInfo(List<Datas.ResultBean.DataBean> news)
    {
        mAdapter.setData(news);
    }

    @Override
    public void showError(String msg)
    {
        Log.e("info", "--------------------->" + msg);
    }
}
