package com.yidou.wandou.example_33.model;

/**
 * Created by Administrator on 2016/12/5.
 */

public class Banners
{
    private String image_url;
    private String image_path;

    public Banners(String image_url, String image_path)
    {
        this.image_url = image_url;
        this.image_path = image_path;
    }

    public String getImage_url()
    {
        return image_url;
    }

    public void setImage_url(String image_url)
    {
        this.image_url = image_url;
    }

    public String getImage_path()
    {
        return image_path;
    }

    public void setImage_path(String image_path)
    {
        this.image_path = image_path;
    }
}
