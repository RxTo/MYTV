package com.example.yuki.yukitv.view.activity

import android.content.Intent
import android.os.Bundle
import cn.droidlover.xdroidmvp.mvp.XPresent
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.view.fragment.*

class ContentActivity: BaseActivity() {
	override fun initData(p0: Bundle?) {
		super.initData(p0)
		switchFragment(intent)
		
	}
	
	override fun getLayoutId(): Int {
		return R.layout.activity_content
	}
	
	override fun newP(): XPresent<*>? {
		return null
	}
	
	
	fun switchFragment(intent: Intent) {
		val fragmentTag = intent.getStringExtra("tag")
		val title = intent.getStringExtra("title")
		val url = intent.getStringExtra("url")
		val uid = intent.getStringExtra("uid")
		val slug = intent.getStringExtra("slug")
		when (fragmentTag) {
			"search" -> supportFragmentManager.beginTransaction().replace(R.id.fragmentContent , SearchFragment()).commit()
			"web" -> supportFragmentManager.beginTransaction().replace(R.id.fragmentContent , WebFragment.newInstance(title , url)).commit()
			"room" -> supportFragmentManager.beginTransaction().replace(R.id.fragmentContent , RoomFragment.newInstance(uid , title)).commit()
			"show" -> supportFragmentManager.beginTransaction().replace(R.id.fragmentContent , ShowFragment.newInstance(uid , url )).commit()
			"login" -> supportFragmentManager.beginTransaction().replace(R.id.fragmentContent , LoginFragment()).commit()
			"more" -> {
				val liveListFragment = LiveListFragment.newInstance(slug , false)
				liveListFragment.isMore = true
				liveListFragment.title = title
				supportFragmentManager.beginTransaction().replace(R.id.fragmentContent , liveListFragment).commit()
			}
			
		}
	}
	
}
