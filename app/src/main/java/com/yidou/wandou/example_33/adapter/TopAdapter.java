package com.yidou.wandou.example_33.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;
import com.yidou.wandou.example_33.Config;
import com.yidou.wandou.example_33.model.Banners;
import com.yidou.wandou.example_33.ui.DetailActivity;
import com.yidou.wandou.example_33.ui.R;
import com.zanlabs.widget.infiniteviewpager.InfinitePagerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/5.
 */

public class TopAdapter extends InfinitePagerAdapter
{
    private Context mContext;
    //    private List<Banners> mList;
    private List<String> banner_img;
    private List<String> banner_url;

    public TopAdapter(Context context, List<String> banner_img, List<String> banner_url)
    {
        mContext = context;
        this.banner_img = banner_img;
        this.banner_url = banner_url;
    }

    @Override
    public int getItemCount()
    {
//        return mList == null ? 0 : mList.size();
        return banner_img == null ? 0 : banner_img.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup container)
    {
        View view;
        ViewsHolder holder;
        if (convertView == null)
        {
            view = LayoutInflater.from(mContext).inflate(R.layout.banner_item, container, false);
            holder = new ViewsHolder(view);
            view.setTag(holder);
        } else
        {
            view = convertView;
            holder = (ViewsHolder) view.getTag();
        }
        holder.mDraweeView.setImageURI(banner_img.get(position));
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(Config.NEWS, banner_url.get(position));
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    class ViewsHolder
    {
        @BindView(R.id.top_image)
        SimpleDraweeView mDraweeView;

        public ViewsHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }
}
