package com.example.yuki.yukitv.view.fragment

import android.os.Bundle
import android.text.TextUtils
import cn.droidlover.xdroidmvp.mvp.XPresent
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * 项目：YukiTv
 * 作者：Yuki  -  2017/5/25
 * 邮箱：125508663@qq.com
 */
class RankFragment: BaseFragment<XPresent<*>>() {
	override fun initData(p0: Bundle?) {
		super.initData(p0)
		initEvent()
	}

	private fun initEvent() {
	
	}

	override fun getLayoutId(): Int {
		return R.layout.fragment_rank
	}

	override fun newP(): XPresent<*>? {
		return HomePresenter()
	}


}
