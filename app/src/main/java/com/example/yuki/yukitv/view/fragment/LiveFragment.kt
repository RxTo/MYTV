package com.example.yuki.yukitv.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.view.View.OnClickListener
import cn.droidlover.xdroidmvp.mvp.XPresent
import cn.droidlover.xdroidmvp.net.ApiSubscriber
import cn.droidlover.xdroidmvp.net.NetError
import cn.droidlover.xdroidmvp.net.XApi
import com.example.yuki.yukitv.R
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
class LiveFragment: BaseFragment<XPresent<*>>() , OnClickListener {
	var mLiveListRecycleAdapter: LiveListRecycleAdapter? = null
	override fun initData(p0: Bundle?) {
		super.initData(p0)
		getLiveList()
		mLiveListRecycleAdapter = LiveListRecycleAdapter(R.layout.item_list_live , false , false)
		mLiveListRecycleAdapter!!.emptyView = mLoadingView
		rvList.layoutManager = GridLayoutManager(context , 2)
		rvList.adapter = mLiveListRecycleAdapter
		srLayout.isEnabled = false
		initEvent()
	}
	
	private fun getLiveList() {
		Api.apiService.allLiveList
				.compose(XApi.getApiTransformer<LiveList>())
				.compose(XApi.getScheduler<LiveList>())
				.compose(bindToLifecycle<LiveList>())
				.subscribe(object: ApiSubscriber<LiveList>() {
					override fun onFail(p0: NetError) {
						p0.printStackTrace()
						onFailTask()
						
					}
					
					override fun onNext(t: LiveList) {
						onLoadLive(t.data)
						srLayout.isEnabled = true
					}
					
					override fun onComplete() {
						srLayout.isRefreshing = false
						
					}
				})
	}
	
	fun onLoadLive(data: List<Live>?) {
		if (data?.size == 0) {
			mLiveListRecycleAdapter?.emptyView = mEmptyView
		} else
			mLiveListRecycleAdapter?.setNewData(data)
	}
	
	fun onFailTask() {
		mLiveListRecycleAdapter?.emptyView = mErrorView
		srLayout.isEnabled = false
	}
	
	private fun initEvent() {
		ivLeft.setOnClickListener(this)
		ivRight.setOnClickListener(this)
		srLayout.setOnRefreshListener {
			getLiveList()
		}
		mLiveListRecycleAdapter?.setOnItemClickListener { _ , _ , position ->
			val intent: Intent
			if (mLiveListRecycleAdapter!!.data[position].screen == 0)
				intent = getIntent("room")
			else {
				intent = getIntent("show")
				intent.putExtra("url" , mLiveListRecycleAdapter!!.data[position].thumb)
			}
			intent.putExtra("uid" , mLiveListRecycleAdapter!!.data[position].uid)
			intent.putExtra("title" , mLiveListRecycleAdapter!!.data[position].title)
			intent.putExtra("view" , mLiveListRecycleAdapter!!.data[position].view)
			startActivity(intent)
		}
		mErrorView.setOnClickListener {
			mLiveListRecycleAdapter?.emptyView = mLoadingView
			getLiveList()
		}
	}
	
	override fun onClick(v: View?) {
		when (v?.id) {
			R.id.ivLeft -> startActivity(getIntent("search"))
			R.id.ivRight -> startActivity(getIntent("login"))
		}
	}
	
	override fun getLayoutId(): Int {
		return R.layout.fragement_live
	}
	
	override fun newP(): XPresent<*>? {
		return null
	}
	
	override fun onDestroy() {
		super.onDestroy()
		
	}
	
}
