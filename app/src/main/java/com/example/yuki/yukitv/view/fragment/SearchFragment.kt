package com.example.yuki.yukitv.view.fragment

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import cn.droidlover.xdroidmvp.mvp.XPresent
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * 项目：YukiTv
 * 作者：Yuki  -  2017/5/25
 * 邮箱：125508663@qq.com
 */
class SearchFragment: BaseFragment<XPresent<*>>() {
	lateinit var liveListFragment: LiveListFragment
	override fun initData(p0: Bundle?) {
		super.initData(p0)
		initEvent()
	}
	
	private fun initEvent() {
		ivLeft.setOnClickListener { mActivity.finish() }
		tvRight.setOnClickListener {
			val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
			imm.hideSoftInputFromWindow(it.windowToken , 0)
			if (TextUtils.isEmpty(etKey.text))
				showWarningMsg("搜索关键字不能为空")
			else {
				liveListFragment = LiveListFragment.newInstance(etKey.text.toString() , true)
				childFragmentManager.beginTransaction().replace(R.id.content , liveListFragment).commit()
			}
		}
	}
	
	override fun getLayoutId(): Int {
		return R.layout.fragment_search
	}
	
	override fun newP(): XPresent<*>? {
		return HomePresenter()
	}
	
	
}
