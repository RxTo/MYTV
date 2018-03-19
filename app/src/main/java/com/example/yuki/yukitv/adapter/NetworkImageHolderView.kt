package com.example.yuki.yukitv.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ImageView.ScaleType.CENTER_CROP
import cn.droidlover.xdroidmvp.imageloader.ILFactory
import cn.droidlover.xdroidmvp.imageloader.ILoader.Options
import com.bigkoo.convenientbanner.holder.Holder
import com.bumptech.glide.Glide
import com.example.yuki.rxtv.mvp.model.Banner.AndroidFocus
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.R.drawable
import com.example.yuki.yukitv.widget.ScaleImageView

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
class NetworkImageHolderView: Holder<AndroidFocus> {
	lateinit var imageView: ScaleImageView
	override fun createView(context: Context): View {
		imageView = ScaleImageView(context)
		imageView.setRatio(3.27f)
		imageView.scaleType = CENTER_CROP
		return imageView
	}
	
	override fun UpdateUI(context: Context , i: Int , androidFocusBean: AndroidFocus) {
		//        ILFactory.getLoader().loadNet(imageView, androidFocusBean.thumb, Options(drawable.live_default, drawable.live_default))
		Glide.with(context).load(androidFocusBean.thumb).dontAnimate().placeholder(R.drawable.live_default).into(imageView)
		
	}
	
	
}
