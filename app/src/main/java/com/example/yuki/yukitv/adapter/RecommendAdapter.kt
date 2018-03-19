package com.example.yuki.yukitv.adapter

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView.ScaleType.CENTER_CROP
import cn.droidlover.xdroidmvp.XDroidConf
import cn.droidlover.xdroidmvp.imageloader.ILFactory
import cn.droidlover.xdroidmvp.imageloader.ILoader
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.R.layout
import com.example.yuki.yukitv.model.Recommend.RoomBean
import com.example.yuki.yukitv.view.activity.ContentActivity


class RecommendAdapter(layoutResId: Int , mContext: Context): BaseQuickAdapter<RoomBean , BaseViewHolder>(layoutResId) {
	
	
	override fun convert(helper: BaseViewHolder , item: RoomBean) {
		helper.run {
			setText(R.id.tvCategory , item.name)
			addOnClickListener(R.id.tvMore)
		}
		Glide.with(mContext).load(item.icon).dontAnimate().placeholder(R.drawable.ic_category_hot).into(helper.getView(R.id.ivMark))
		//		ILFactory.getLoader().loadNet(helper.getView(R.id.ivMark) , item.icon , ILoader.Options(XDroidConf.IL_LOADING_RES , R.drawable.ic_category_hot))
		val recyclerView = helper.getView<RecyclerView>(R.id.rvItem)
		recyclerView.layoutManager = GridLayoutManager(mContext , 2)
		val adapter: RecommendItemAdapter
		if (item.name == "Showing")
			adapter = RecommendItemAdapter(layout.item_list_show , item.list , true)
		else
			adapter = RecommendItemAdapter(R.layout.item_list_live , item.list , false)
		recyclerView.adapter = adapter
		(recyclerView.adapter as RecommendItemAdapter).setOnItemClickListener { _ , _ , position ->
			val intent = Intent(mContext , ContentActivity::class.java)
			if (adapter.data[position].screen == 0)
				intent.putExtra("tag" , "room")
			else {
				intent.putExtra("url" , adapter.data[position].thumb)
				intent.putExtra("tag" , "show")
			}
			intent.putExtra("uid" , adapter.data[position].uid)
			intent.putExtra("title" , adapter.data[position].title)
			intent.putExtra("view" , adapter.data[position].view)
			
			mContext.startActivity(intent)
		}
	}
}
