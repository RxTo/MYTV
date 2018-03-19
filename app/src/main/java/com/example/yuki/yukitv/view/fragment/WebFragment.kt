package com.example.yuki.yukitv.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import cn.droidlover.xdroidmvp.mvp.XPresent
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_web.*

/**
 * 项目：YukiTV
 * 作者：Yuki - 2017/6/6
 * 邮箱：125508663@qq.com
 */
class WebFragment(var title: String , var url: String): BaseFragment<XPresent<*>>() {
	
	
	companion object {
		fun newInstance(title: String , url: String): WebFragment {
			return WebFragment(title , url)
		}
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	override fun initData(p0: Bundle?) {
		super.initData(p0)
		tvTitle.text=title
		progressBar.max=100
		val ws = webView.settings
		//是否允许脚本支持
		ws.javaScriptEnabled = true
		ws.domStorageEnabled = true
		ws.javaScriptCanOpenWindowsAutomatically = true
		webView.setWebChromeClient(object: WebChromeClient() {
			override fun onProgressChanged(view: WebView , newProgress: Int) {
				super.onProgressChanged(view , newProgress)
				progressBar.progress=newProgress
				if (progressBar.progress==100){
					progressBar.visibility= View.GONE
				}
			}
		})
		webView.loadUrl(url)
		initEvent()
	}
	
	private fun initEvent() {
		ivLeft.setOnClickListener { mActivity.finish() }
		layoutError.setOnClickListener {
			layoutError.visibility=View.VISIBLE
			progressBar.visibility=View.VISIBLE
			webView.reload()
		}
	}
	
	
	override fun getLayoutId(): Int {
		return R.layout.fragment_web
	}
	
	override fun newP(): XPresent<*>? {
		return HomePresenter()
	}
	
}