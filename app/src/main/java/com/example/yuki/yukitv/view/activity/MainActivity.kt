package com.example.yuki.yukitv.view.activity

import android.os.Bundle
import android.view.KeyEvent
import cn.droidlover.xdroidmvp.mvp.XPresent
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationBar.OnTabSelectedListener
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.view.fragment.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

/**
 * 作者：Yuki  -  2017/5/24
 * 邮箱：125508663@qq.com
 
 */
class MainActivity: BaseActivity() {
	var mIsExit: Boolean = false
	val fragmentList = arrayOfNulls<BaseFragment<*>>(4)
	override fun initData(p0: Bundle?) {
		super.initData(p0)
		bottom_navigation_bar
				.setMode(BottomNavigationBar.MODE_FIXED)
				.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
				.addItem(BottomNavigationItem(R.drawable.btn_tabbar_home_normal , "首页"))
				.addItem(BottomNavigationItem(R.drawable.btn_tabbar_zhibo_normal , "直播"))
				.addItem(BottomNavigationItem(R.drawable.btn_tabbar_guanzhu_normal , "关注"))
				.addItem(BottomNavigationItem(R.drawable.btn_tabbar_wode_normal , "我的")).initialise()
		fragmentList[0] = HomeFragment()
		supportFragmentManager.beginTransaction().replace(R.id.content , fragmentList[0]).commit()
	}
	
	override fun bindEvent() {
		bottom_navigation_bar.setTabSelectedListener(object: OnTabSelectedListener {
			
			override fun onTabSelected(i: Int) {
				val beginTransaction = supportFragmentManager.beginTransaction()
				if (fragmentList[i] == null) {
					when (i) {
						1 -> fragmentList[1] = (LiveFragment())
						2 -> fragmentList[2] = (FollowerFragment())
						3 -> fragmentList[3] = (MineFragment())
					}
					beginTransaction.add(R.id.content , fragmentList[i]).commit()
				} else
					beginTransaction.show(fragmentList[i]).commit()
			}
			
			override fun onTabUnselected(i: Int) {
				supportFragmentManager.beginTransaction().hide(fragmentList[i]).commit()
				
			}
			
			override fun onTabReselected(i: Int) {
				
			}
		})
	}
	
	override fun onKeyDown(keyCode: Int , event: KeyEvent): Boolean {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit()
			return false
		}
		return super.onKeyDown(keyCode , event)
	}
	
	
	private fun exit() {
		if (mIsExit) {
			finish()
			System.exit(0)
		} else {
			mIsExit = true
			showWarningMsg("再按一次退出程序")
			//两秒内不点击back则重置mIsExit
			
			Observable.timer(2000 , TimeUnit.SECONDS)
					.observeOn(Schedulers.io())
					.subscribeOn(AndroidSchedulers.mainThread())
					.compose(bindToLifecycle()).subscribe { mIsExit = false }
		}
	}
	
	override fun getLayoutId(): Int {
		return R.layout.activity_main
	}
	
	override fun newP(): XPresent<*>? {
		return null
	}
	
	
}
