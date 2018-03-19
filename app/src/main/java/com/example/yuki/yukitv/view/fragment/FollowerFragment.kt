package com.example.yuki.yukitv.view.fragment

import android.content.Intent.getIntent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.view.View.OnClickListener
import cn.droidlover.xdroidmvp.mvp.XPresent
import cn.droidlover.xdroidmvp.net.ApiSubscriber
import cn.droidlover.xdroidmvp.net.NetError
import cn.droidlover.xdroidmvp.net.XApi
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.R.id.ivLeft
import com.example.yuki.yukitv.R.id.ivRight
import com.example.yuki.yukitv.adapter.LiveListRecycleAdapter
import com.example.yuki.yukitv.model.LiveList
import com.example.yuki.yukitv.model.LiveList.Live
import com.example.yuki.yukitv.net.Api
import kotlinx.android.synthetic.main.fragement_live.*

/**
 * 项目：YukiTv
 * 作者：Yuki  -  2017/5/25
 * 邮箱：125508663@qq.com
 */
class FollowerFragment: BaseFragment<XPresent<*>>() , OnClickListener {
	override fun initData(p0: Bundle?) {
		super.initData(p0)
		initEvent()
	}
	
	
	private fun initEvent() {
		ivLeft.setOnClickListener(this)
		ivRight.setOnClickListener(this)
		
	}
	
	override fun onClick(v: View?) {
		when (v?.id) {
			R.id.ivLeft -> startActivity(getIntent("search"))
			R.id.ivRight -> startActivity(getIntent("login"))
		}
	}
	
	override fun getLayoutId(): Int {
		return R.layout.fragement_follower
	}
	
	override fun newP(): XPresent<*>? {
		return null
	}
	
	
}
