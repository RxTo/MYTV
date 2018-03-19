package com.example.yuki.yukitv.presenter

import android.util.Log
import cn.droidlover.xdroidmvp.net.ApiSubscriber
import cn.droidlover.xdroidmvp.net.NetError
import cn.droidlover.xdroidmvp.net.XApi
import com.example.yuki.rxtv.mvp.model.Banner
import com.example.yuki.yukitv.model.LiveList
import com.example.yuki.yukitv.model.Recommend
import com.example.yuki.yukitv.model.SearchBody
import com.example.yuki.yukitv.model.SearchBody.P
import com.example.yuki.yukitv.model.SearchResult
import com.example.yuki.yukitv.net.Api
import com.example.yuki.yukitv.view.fragment.LiveListFragment
import io.reactivex.Flowable
import kotlinx.android.synthetic.main.fragement_list_live.*

class LiveListPresent: BasePresenter<LiveListFragment>() {
	fun getRecommend() {
		Api.apiService.recommend.compose(XApi.getApiTransformer<Recommend>()).compose(XApi.getScheduler<Recommend>()).compose(v.bindToLifecycle<Recommend>()).doOnNext {
			it.room?.filter { it.list!!.isEmpty() }?.forEach { room -> it.room?.remove(room) }
			it.room?.sortByDescending { it.list?.size }
		}.subscribe(object: ApiSubscriber<Recommend>() {
			override fun onFail(p0: NetError) {
				p0.printStackTrace()
				v.onFailTask()
			}
			
			override fun onNext(t: Recommend) {
				v.onLoadRecommend(t.room)
				v.srLayout.isEnabled = true
				
			}
			
			override fun onComplete() {
				v.srLayout.isRefreshing = false
			}
			
		})
		getBanner()
		
	}
	
	private fun getBanner() {
		Api.apiService.banner
				.compose(XApi.getApiTransformer<Banner>())
				.compose(XApi.getScheduler<Banner>())
				.compose(v.bindToLifecycle<Banner>())
				.subscribe(object: ApiSubscriber<Banner>() {
					override fun onFail(p0: NetError) {
					}
					
					override fun onNext(t: Banner) {
						v.onLoadBanner(t.androidFocus)
					}
					
				})
	}
	
	
	fun getLiveList(slug: String?) {
		if (slug == "recommend") getRecommend()
		else {
			val liveList: Flowable<LiveList>
			if (slug != "all") {
				liveList = Api.apiService.getLiveList(slug)
			} else liveList = Api.apiService.allLiveList
			liveList
					.compose(XApi.getApiTransformer<LiveList>())
					.compose(XApi.getScheduler<LiveList>())
					.compose(v.bindToLifecycle<LiveList>())
					.subscribe(object: ApiSubscriber<LiveList>() {
						override fun onFail(p0: NetError) {
							p0.printStackTrace()
							v.onFailTask()
							
						}
						
						override fun onNext(t: LiveList) {
							v.onLoadLive(t.data)
							v.srLayout.isEnabled = true
						}
						
						override fun onComplete() {
							v.srLayout.isRefreshing = false
							
						}
					})
		}
		
	}
	
	fun getLiveListByKey(key: String) {
		Api.apiService.search(searchBody = SearchBody(P(key)))
				.compose(XApi.getApiTransformer<SearchResult>())
				.compose(XApi.getScheduler<SearchResult>())
				.compose(v.bindToLifecycle<SearchResult>())
				.subscribe(object : ApiSubscriber<SearchResult>(){
					override fun onFail(p0: NetError?) {
						v.onFailTask()
					}
					
					override fun onNext(t: SearchResult?) {
						v.onLoadLive(t?.data?.items)
					}
					
				})
		
		
	}
	
	
}
