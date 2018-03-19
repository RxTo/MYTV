package com.example.yuki.yukitv.net


import com.example.yuki.rxtv.mvp.model.Banner
import com.example.yuki.rxtv.mvp.model.LiveCategory
import com.example.yuki.yukitv.model.*
import com.example.yuki.yukitv.model.LiveList.Live
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * 项目：YukiTV
 * 作者：Yuki  -  2016/12/31
 * 邮箱：125508663@qq.com
 */

interface ApiService {
	
	/**
	 * 获取推荐列表
	 
	 * @return
	 */
	@get:GET("json/app/index/recommend/list-android.json")
	val recommend: Flowable<Recommend>
	
	/**
	 * 获取
	 
	 * @return categories/list.json
	 */
	@get:GET("json/app/index/category/info-android.json")
	val allCategories: Observable<List<LiveCategory>>
	
	
	/*
	* 获取分类列表
	*
	* */
	@GET("json/categories/{slug}/list.json")
	fun getLiveList(@Path("slug") slug: String?): Flowable<LiveList>
	
	/**
	 * 获取直播全部列表
	 * @return
	 */
	@get:GET("json/play/list.json")
	val allLiveList: Flowable<LiveList>
	
	/**
	 * 获取App启动页信息
	 * @return
	 */
	@get:GET("/json/page/app_images")
	val banner: Flowable<Banner>
	
	@POST("site/search")
	fun search(@Body searchBody: SearchBody): Flowable<SearchResult>
	
	/**
	 * 获取房间信息
	 * @return
	 */
	@GET("json/rooms/{uid}/info.json")
	fun enterRoom(@Path("uid") uid: String): Flowable<Room>
}

