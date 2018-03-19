package com.example.yuki.yukitv.view.fragment

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import cn.droidlover.xdroidmvp.mvp.XPresent
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_chat.*

/**
 * 项目：YukiTv
 * 作者：Yuki  -  2017/5/25
 * 邮箱：125508663@qq.com
 */
class ChatFragment: BaseFragment<XPresent<*>>() {
	override fun initData(p0: Bundle?) {
		super.initData(p0)
		val ssb = SpannableStringBuilder()
		//系统通知图片
		val imageSpan = ImageSpan(context , R.drawable.img_dm_xttz)
		val spannableString = SpannableString("123")
		spannableString.setSpan(imageSpan , 0 , spannableString.length , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
		ssb.append(spannableString)
		//系统通知内容
		ssb.append(" 全民直播提倡绿色直播。封页和直播内容含吸烟，低俗，引诱，暴露等都将被封停账号。网警在线24小时巡查哦。")
		tvTips.text=ssb
		initEvent()
	}
	
	private fun initEvent() {
	
	}
	
	override fun getLayoutId(): Int {
		return R.layout.fragment_chat
	}
	
	override fun newP(): XPresent<*>? {
		return HomePresenter()
	}
	
	
}
