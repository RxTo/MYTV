package com.example.yuki.yukitv.net

import cn.droidlover.xdroidmvp.net.XApi

/**
* 项目：YukiTV
* 作者：Yuki - 2016/12/31
* 邮箱：125508663@qq.com
*/


object Api{
    val base_url = "http://www.quanmin.tv/"
    val apiService: ApiService=XApi.getInstance().getRetrofit(base_url, true).create(ApiService::class.java)
}