package com.example.yuki.yukitv.view.fragment

import android.os.Bundle
import cn.droidlover.xdroidmvp.XDroidConf
import cn.droidlover.xdroidmvp.imageloader.ILFactory
import cn.droidlover.xdroidmvp.imageloader.ILoader.Options
import cn.droidlover.xdroidmvp.mvp.XPresent
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.R.drawable
import com.example.yuki.yukitv.model.Room
import com.example.yuki.yukitv.utils.DecimalFormatUtil
import kotlinx.android.synthetic.main.fragment_anchor.*

/**
 * 项目：YukiTv
 * 作者：Yuki  -  2017/5/25
 * 邮箱：125508663@qq.com
 */
class AnchorFragment: BaseFragment<XPresent<*>>() {
	var room: Room? = null
	override fun initData(p0: Bundle?) {
		super.initData(p0)
		if (room!=null)
			onUpdateAnchor(room!!)
	}
	
	fun onUpdateAnchor(room: Room) {
		ILFactory.getLoader().loadNet(civAvatar , room.avatar , Options(XDroidConf.IL_LOADING_RES , drawable.mine_default_avatar))
		tvAnchorName.text = room.nick
		tvAccount.text = room.no.toString()
		tvFans.text = room.follow.toString()
		tvWeight.text = DecimalFormatUtil.formatW(room.weight / 100)
		tvStartLiveTime.text = room.announcement
	}
	
	override fun getLayoutId(): Int {
		return R.layout.fragment_anchor
	}
	
	override fun newP(): XPresent<*>? {
		return null
	}
	
	
}
