package com.yidou.wandou.example_33.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.yidou.wandou.example_33.fragments.GuanzhuFragment;
import com.yidou.wandou.example_33.fragments.HomeFragment;
import com.yidou.wandou.example_33.fragments.MineFragment;
import com.yidou.wandou.example_33.fragments.TixingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
{


    @BindView(R.id.main_frame)
    FrameLayout mMainFrame;
    @BindView(R.id.main_bottomBar)
    BottomNavigationBar mMainBottomBar;

    private Context mContext;
    private FragmentManager fm;

    private HomeFragment mHomeFragment;
    private GuanzhuFragment mGuanzhuFragment;
    private TixingFragment mTixingFragment;
    private MineFragment mMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        fm = getSupportFragmentManager();
        initBottom();
    }

    private void initBottom()
    {
        mMainBottomBar.setMode(BottomNavigationBar.MODE_FIXED);
        mMainBottomBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mMainBottomBar.addItem(new BottomNavigationItem(R.drawable.cb_icon_discover_selected, "首页")
                        .setActiveColorResource(R.color.colorBG).setInactiveIcon(mContext.getResources().getDrawable(R.drawable.cb_icon_discover_normal)))
                .addItem(new BottomNavigationItem(R.drawable.cb_icon_guanzhu_selected, "关注")
                        .setActiveColorResource(R.color.colorBG).setInactiveIcon(mContext.getResources().getDrawable(R.drawable.cb_icon_guanzhu_normal)))
                .addItem(new BottomNavigationItem(R.drawable.cb_icon_tixing_selected, "消息")
                        .setActiveColorResource(R.color.colorBG).setInactiveIcon(mContext.getResources().getDrawable(R.drawable.cb_icon_tixing_normal)))
                .addItem(new BottomNavigationItem(R.drawable.cb_icon_more_selected, "我的")
                        .setActiveColorResource(R.color.colorBG).setInactiveIcon(mContext.getResources().getDrawable(R.drawable.cb_icon_more_normal)))
                .setFirstSelectedPosition(0)
                .initialise();

        mMainBottomBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(int position)
            {
                switch (position)
                {
                    case 0:
                        showFragment(0);
                        break;
                    case 1:
                        showFragment(1);
                        break;
                    case 2:
                        showFragment(2);
                        break;
                    case 3:
                        showFragment(3);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position)
            {
            }

            @Override
            public void onTabReselected(int position)
            {
            }
        });
        showFragment(0);
    }

    /**
     * 展示fragment
     *
     * @param position
     */
    private void showFragment(int position)
    {
        FragmentTransaction ft = fm.beginTransaction();
        hideFragments(ft);
        switch (position)
        {
            case 0:
                if (mHomeFragment != null)
                {
                    ft.show(mHomeFragment);
                } else
                {
                    mHomeFragment = new HomeFragment();
                    ft.add(R.id.main_frame, mHomeFragment);
                }
                break;
            case 1:
                if (mGuanzhuFragment != null)
                {
                    ft.show(mGuanzhuFragment);
                } else
                {
                    mGuanzhuFragment = new GuanzhuFragment();
                    ft.add(R.id.main_frame, mGuanzhuFragment);
                }
                break;
            case 2:
                if (mTixingFragment != null)
                {
                    ft.show(mTixingFragment);
                } else
                {
                    mTixingFragment = new TixingFragment();
                    ft.add(R.id.main_frame, mTixingFragment);
                }
                break;
            case 3:
                if (mMineFragment != null)
                {
                    ft.show(mMineFragment);
                } else
                {
                    mMineFragment = new MineFragment();
                    ft.add(R.id.main_frame, mMineFragment);
                }
                break;
            default:
                break;
        }
        ft.commit();
    }

    private void hideFragments(FragmentTransaction ft)
    {
        if (mHomeFragment != null)
        {
            ft.hide(mHomeFragment);
        }
        if (mGuanzhuFragment != null)
        {
            ft.hide(mGuanzhuFragment);
        }
        if (mTixingFragment != null)
        {
            ft.hide(mTixingFragment);
        }
        if (mMineFragment != null)
        {
            ft.hide(mMineFragment);
        }
    }
}
