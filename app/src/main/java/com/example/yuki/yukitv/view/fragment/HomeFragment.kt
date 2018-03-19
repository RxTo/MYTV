package com.example.yuki.yukitv.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.View.OnClickListener
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.adapter.FragmentAdapter
import com.example.yuki.yukitv.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 项目：YukiTv
 * 作者：Yuki  -  2017/5/25
 * 邮箱：125508663@qq.com
 */
class HomeFragment: BaseFragment<HomePresenter>(), OnClickListener {
	var mListCategory = ArrayList<Fragment>()
	var mListTitle = ArrayList<String?>()
	override fun initData(p0: Bundle?) {
		super.initData(p0)
		viewPager.adapter = FragmentAdapter(fragmentManager, mListTitle, mListCategory)
		tabLayout.setupWithViewPager(viewPager)
		p.getAllCategory()
		initEvent()
	}
	
	private fun initEvent() {
		ivLeft.setOnClickListener(this)
		ivRight.setOnClickListener(this)
	}
	
	
	override fun onClick(v: View?) {
		when (v?.id) {
			R.id.ivLeft -> startActivity(getIntent("search"))
			R.id.ivRight -> startActivity(getIntent("login"))
		}
	}
	
	
	override fun getLayoutId(): Int {
		return R.layout.fragment_home
	}
	
	override fun newP(): HomePresenter? {
		return HomePresenter()
	}
	
	
	fun onLoadData(titleList: ArrayList<String?>, listCategory: ArrayList<BaseFragment<*>>) {
		mListCategory.clear()
		mListTitle.clear()
		mListTitle.addAll(titleList)
		mListCategory.addAll(listCategory)
		viewPager.adapter.notifyDataSetChanged()
	}
	
}
