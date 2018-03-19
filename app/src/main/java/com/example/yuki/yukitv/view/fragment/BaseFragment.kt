package com.example.yuki.yukitv.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import cn.droidlover.xdroidmvp.mvp.IPresent
import cn.droidlover.xdroidmvp.mvp.XFragment
import com.example.yuki.yukitv.R.layout
import com.example.yuki.yukitv.view.activity.BaseActivity
import com.example.yuki.yukitv.view.activity.ContentActivity

/**
 * 作者：Yuki  -  2017/5/24
 * 邮箱：125508663@qq.com
 */
abstract class BaseFragment<P: IPresent<*>>: XFragment<P>() {
	lateinit var mActivity: BaseActivity
	lateinit var mErrorView: View
	lateinit var mLoadingView: View
	lateinit var mEmptyView: View
	override fun initData(p0: Bundle?) {
		mActivity = activity as BaseActivity
		mErrorView = View.inflate(mActivity, layout.layout_error, null)
		mLoadingView = View.inflate(mActivity, layout.layout_loading, null)
		mEmptyView = View.inflate(mActivity, layout.layout_empty, null)
	}

	
	fun showErrorMsg(msg: String) {
		mActivity.showErrorMsg(msg)
	}
	fun showWarningMsg(msg: String){
		mActivity.showWarningMsg(msg)
	}
	
	fun getIntent(tag: String): Intent {
		val intent = Intent(getContext(), ContentActivity::class.java)
		intent.putExtra("tag", tag)
		return intent
	}
}
