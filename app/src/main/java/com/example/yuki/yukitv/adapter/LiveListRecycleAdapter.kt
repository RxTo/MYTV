package com.example.yuki.yukitv.adapter


import android.view.View
import android.widget.ImageView.ScaleType.CENTER_CROP
import android.widget.TextView
import cn.droidlover.xdroidmvp.XDroidConf
import cn.droidlover.xdroidmvp.imageloader.ILFactory
import cn.droidlover.xdroidmvp.imageloader.ILoader.Options
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.model.LiveList.Live
import com.example.yuki.yukitv.R.drawable
import com.example.yuki.yukitv.R.id

class LiveListRecycleAdapter(layoutResId: Int , val isShow: Boolean , val isStatus: Boolean): BaseQuickAdapter<Live , BaseViewHolder>(layoutResId) {
	
	override fun convert(helper: BaseViewHolder , item: Live) {
		helper.run {
			setText(id.tvName , item.nick)
			setText(id.tvTitle , item.title)
			setText(id.tvViewer , item.view)
			if (isStatus) setVisible(id.tvStatus , item.play_status)
			if (isShow) setText(id.tvLocation , item.position)
		}
		Glide.with(mContext).load(item.thumb).dontAnimate().placeholder(R.drawable.live_default).into(helper.getView(id.ivThumb))
		//		ILFactory.getLoader().loadNet(helper.getView(id.ivThumb) , item.thumb , Options(XDroidConf.IL_LOADING_RES , drawable.live_default).scaleType(CENTER_CROP))
		
	}
}
