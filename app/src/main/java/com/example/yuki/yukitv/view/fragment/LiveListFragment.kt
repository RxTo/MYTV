package com.example.yuki.yukitv.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bigkoo.convenientbanner.ConvenientBanner
import com.example.yuki.rxtv.mvp.model.Banner.AndroidFocus
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.adapter.LiveListRecycleAdapter
import com.example.yuki.yukitv.adapter.NetworkImageHolderView
import com.example.yuki.yukitv.adapter.RecommendAdapter
import com.example.yuki.yukitv.model.LiveList.Live
import com.example.yuki.yukitv.model.Recommend.RoomBean
import com.example.yuki.yukitv.presenter.LiveListPresent
import kotlinx.android.synthetic.main.fragement_list_live.*

/**
 * 作者：Yuki  -  2017/5/16
 * 邮箱：125508663@qq.com
 */

class LiveListFragment: BaseFragment<LiveListPresent>() {
	
	
	var slug: String = ""
	var isSearch: Boolean = false
	var isMore: Boolean = false
	var title: String = ""
	var mBanner: ConvenientBanner<AndroidFocus>? = null
	var mTopPic = ArrayList<AndroidFocus>()
	var mRecommendAdapter: RecommendAdapter? = null
	var mLiveListRecycleAdapter: LiveListRecycleAdapter? = null
	override fun onStart() {
		super.onStart()
		if (mBanner != null)
			mBanner!!.startTurning(3000)
	}
	
	override fun onStop() {
		super.onStop()
		if (mBanner != null)
			mBanner!!.stopTurning()
	}
	
	override fun initData(p0: Bundle?) {
		super.initData(p0)
		if (isMore) {
			val tvTitle = view?.findViewById(R.id.tvTitle) as TextView
			tvTitle.text = title
			val ivLeft = view?.findViewById(R.id.ivLeft) as ImageView
			ivLeft.setOnClickListener { activity.finish() }
		}
		when (slug) {
			"recommend" -> {
				val headView = View.inflate(context , R.layout.banner , null)
				mBanner = headView.findViewById(R.id.banner) as ConvenientBanner<AndroidFocus>
				
				mRecommendAdapter = RecommendAdapter(R.layout.item_list_remmend , activity)
				mRecommendAdapter!!.addHeaderView(headView)
				mRecommendAdapter!!.emptyView = mLoadingView
				rvList.layoutManager = LinearLayoutManager(context)
				rvList.adapter = mRecommendAdapter
			}
			else -> {
				val isShow = slug == "showing"
				mLiveListRecycleAdapter = LiveListRecycleAdapter(if (!isShow) R.layout.item_list_live else R.layout.item_list_show , isShow , isSearch)
				mLiveListRecycleAdapter!!.emptyView = mLoadingView
				rvList.layoutManager = GridLayoutManager(context , 2)
				rvList.adapter = mLiveListRecycleAdapter
			}
		}
		
		srLayout.isEnabled = false
		if (isSearch)
			p.getLiveListByKey(slug)
		else
			p.getLiveList(slug)
		initEvent()
	}
	
	
	fun initEvent() {
		srLayout.setOnRefreshListener {
			p.getLiveList(slug)
		}
		mErrorView.setOnClickListener {
			mLiveListRecycleAdapter?.emptyView = mLoadingView
			mRecommendAdapter?.emptyView = mLoadingView
			p.getLiveList(slug)
		}
		mBanner?.setOnItemClickListener {
			var intent = getIntent("room")
			if (mTopPic[it].ext?.type == "play") {
				intent.putExtra("uid" , mTopPic[it].uid)
				intent.putExtra("title" , mTopPic[it].title)
				
			} else {
				intent = getIntent("web")
				intent.putExtra("url" , mTopPic[it].link)
				intent.putExtra("title" , mTopPic[it].title)
				
			}
			startActivity(intent)
			
		}
		mRecommendAdapter?.setOnItemChildClickListener { _ , _ , position ->
			val intent = getIntent("more")
			intent.putExtra("title" , mRecommendAdapter!!.data[position].name)
			if (position == 0)
				intent.putExtra("slug" , "all")
			else
				intent.putExtra("slug" , mRecommendAdapter!!.data[position].slug)
			startActivity(intent)
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
			startActivity(intent)
		}
	}
	
	
	override fun getLayoutId(): Int {
		return if (isMore) R.layout.fragement_list_live_more else R.layout.fragement_list_live
	}
	
	override fun newP(): LiveListPresent? {
		return LiveListPresent()
	}
	
	companion object {
		@JvmOverloads fun newInstance(slug: String , isSearch: Boolean = false): LiveListFragment {
			val fragment = LiveListFragment()
			fragment.slug = slug
			fragment.isSearch = isSearch
			return fragment
		}
	}
	
	
	fun onFailTask() {
		mRecommendAdapter?.emptyView = mErrorView
		mLiveListRecycleAdapter?.emptyView = if (isSearch) mEmptyView else mErrorView
		srLayout.isEnabled = false
	}
	
	var first: Boolean = true
	
	fun onLoadBanner(data: List<AndroidFocus>?) {
		if (data != null) {
			mTopPic.clear()
			mTopPic.addAll(data)
		}
		if (first) {
			first = false
			mBanner?.setPages({ NetworkImageHolderView() } , mTopPic)
					?.startTurning(3000)
					?.setPageIndicator(intArrayOf(R.drawable.ic_dot_normal , R.drawable.ic_dot_selected))
					?.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
		} else
			mBanner?.notifyDataSetChanged()
	}
	
	fun onLoadRecommend(room: List<RoomBean>?) {
		mRecommendAdapter?.setNewData(room)
		
	}
	
	fun onLoadLive(data: List<Live>?) {
		if (data?.size == 0) {
			mLiveListRecycleAdapter?.emptyView = mEmptyView
		} else
			mLiveListRecycleAdapter?.setNewData(data)
	}
}


