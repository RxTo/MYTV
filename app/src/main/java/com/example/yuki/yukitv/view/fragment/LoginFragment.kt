package com.example.yuki.yukitv.view.fragment

import android.os.Bundle
import cn.droidlover.xdroidmvp.mvp.XPresent
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * 项目：YukiTV
 * 作者：Yuki - 2017/6/6
 * 邮箱：125508663@qq.com
 */
class LoginFragment : BaseFragment<XPresent<*>>() {
	
	override fun initData(p0: Bundle?) {
		super.initData(p0)
		initEvent()
	}
	
	private fun initEvent() {
		ivLeft.setOnClickListener { mActivity.onBackPressed() }
	}
	
	override fun getLayoutId(): Int {
		return R.layout.fragment_login
	}
	
	override fun newP(): XPresent<*>? {
		return HomePresenter()
	}
	
}