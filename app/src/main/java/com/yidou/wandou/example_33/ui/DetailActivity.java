package com.yidou.wandou.example_33.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.yidou.wandou.example_33.Config;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity
{

    @BindView(R.id.detail_toolbar)
    Toolbar mDetailToolbar;
    @BindView(R.id.detail_webView)
    WebView mDetailWebView;
    @BindView(R.id.detail_info)
    ImageView mDetailInfo;
    @BindView(R.id.detail_like)
    ImageView mDetailLike;
    @BindView(R.id.detail_share)
    ImageView mDetailShare;

    boolean isLike = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initToolBar();
        initUrl();
        initImageClick();
    }

    private void initImageClick()
    {
        mDetailLike.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!isLike)
                {
                    mDetailLike.setImageDrawable(getResources().getDrawable(R.drawable.icon_like_selected));
                    isLike = true;
                }else
                {
                    mDetailLike.setImageDrawable(getResources().getDrawable(R.drawable.hot_icon_like_night));
                    isLike = false;
                }
            }
        });
        mDetailShare.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(DetailActivity.this, "分享成功！", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initUrl()
    {
        Intent it = getIntent();
        mDetailWebView.loadUrl(it.getStringExtra(Config.NEWS));
    }

    private void initToolBar()
    {
        setSupportActionBar(mDetailToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.detail_menu_info:
                Toast.makeText(this, "如果您有什么好的建议或者不懂，请联系1070138445！~", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }
}
