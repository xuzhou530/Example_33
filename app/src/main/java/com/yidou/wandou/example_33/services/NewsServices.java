package com.yidou.wandou.example_33.services;


import com.yidou.wandou.example_33.model.Datas;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/5.
 */

public interface NewsServices
{

    @GET("index?type=top&key=988e51c45bbe88a33e032f8da38a61dd")
    Observable<Datas> getNewsService();
}
