package com.example.yuki.rxtv.mvp.model

import cn.droidlover.xdroidmvp.net.IModel
import com.google.gson.annotations.SerializedName

/**
 * 项目：YukiTV
 * 作者：Yuki - 2017/5/17
 * 邮箱：125508663@qq.com
 */

class Banner: IModel {
	
	@SerializedName("android-focus")
	var androidFocus: List<AndroidFocus>? = null
	
	@SerializedName("android-launch-image")
	var androidImage: List<AndroidImage>? = null
	
	override fun isNull(): Boolean {
		return false
	}
	
	override fun isAuthError(): Boolean {
		return false
	}
	
	override fun isBizError(): Boolean {
		return false
	}
	
	override fun getErrorMsg(): String? {
		return null
	}
	
	class Ext {
		var type: String? = null
	}
	
	class AndroidFocus {
		var ext: Ext? = null
		var width: Int = 0
		var height: Int = 0
		var title: String? = null
		var thumb: String? = null
		var type: String? = null
		var link: String? = null
		var uid: String? = null
		
	}
	
	
	class AndroidImage {
		
		var type: String? = null
		var title: String? = null
		var thumb: String? = null
		var time: String? = null
		var start_time: String? = null
		var end_time: String? = null
		var link: String? = null
	}
}
