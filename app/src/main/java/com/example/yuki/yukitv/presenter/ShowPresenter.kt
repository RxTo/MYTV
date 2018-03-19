package com.example.yuki.yukitv.presenter

import cn.droidlover.xdroidmvp.net.ApiSubscriber
import cn.droidlover.xdroidmvp.net.NetError
import cn.droidlover.xdroidmvp.net.XApi
import com.example.yuki.yukitv.model.Room
import com.example.yuki.yukitv.net.Api
import com.example.yuki.yukitv.view.fragment.RoomFragment
import com.example.yuki.yukitv.view.fragment.ShowFragment

/**
 * 项目：YukiTV
 * 作者：Yuki - 2017/6/8
 * 邮箱：125508663@qq.com
 */
class ShowPresenter: BasePresenter<ShowFragment>() {
	fun enterRoom(uid: String ) {
		Api.apiService.enterRoom(uid)
				.compose(XApi.getApiTransformer<Room>())
				.compose(XApi.getScheduler<Room>())
				.compose(v.bindToLifecycle())
				.subscribe(object: ApiSubscriber<Room>() {
					override fun onNext(t: Room?) {
						v.enterRoom(t)
					}
					
					override fun onFail(p0: NetError?) {
						println(p0)
					}
					
				})
	}
}