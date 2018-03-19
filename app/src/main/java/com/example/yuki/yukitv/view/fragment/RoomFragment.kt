package com.example.yuki.yukitv.view.fragment

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils.isEmpty
import android.view.KeyEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.adapter.FragmentAdapter
import com.example.yuki.yukitv.model.Room
import com.example.yuki.yukitv.presenter.RoomPresenter
import com.example.yuki.yukitv.utils.UIutil
import com.pili.pldroid.player.widget.PLVideoView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_room.*
import java.util.concurrent.TimeUnit.SECONDS


/**
 * 项目：YukiTV
 * 作者：Yuki - 2017/6/6
 * 邮箱：125508663@qq.com
 */
class RoomFragment(val uid: String , val title: String): BaseFragment<RoomPresenter>() {
	var mListData = ArrayList<Fragment>()
	var mListTitle = ArrayList<String?>()
	lateinit var anchorFragment: AnchorFragment
	var isControllerHidden = false
	var mObservable: Observable<Long> = Observable.timer(5 , SECONDS)
	lateinit var mDisposable: Disposable
	
	companion object {
		fun newInstance(uid: String , title: String): RoomFragment {
			return RoomFragment(uid , title)
		}
	}
	
	override fun initData(p0: Bundle?) {
		super.initData(p0)
		
		mListTitle.add("聊天")
		mListTitle.add("排行")
		mListTitle.add("主播")
		mListData.add(ChatFragment())
		mListData.add(RankFragment())
		anchorFragment = AnchorFragment()
		mListData.add(anchorFragment)
		tvRoomTitle.text = title
		viewPager.adapter = FragmentAdapter(childFragmentManager , mListTitle , mListData)
		tabLayout.setupWithViewPager(viewPager)
		p.enterRoom(uid)
		initEvent()
	}
	
	private fun initEvent() {
		mDisposable = timeToBar()
		videoContent.setOnClickListener({
			if (isControllerHidden) {
				showVideoToolbar()
			} else {
				hideVideoToolbar()
			}
		})
		ivBack.setOnClickListener {
			onBackPressed()
		}
		ivShare.setOnClickListener {
			startActivity(getIntent("login"))
		}
		ivFullScreen.setOnClickListener { activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE }
	}
	
	private fun timeToBar(): Disposable {
		return mObservable
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.compose(bindToLifecycle())
				.subscribe {
					hideVideoToolbar()
				}
	}
	
	private fun showVideoToolbar() {
		videoToolBar.visibility = VISIBLE
		isControllerHidden = false
		mDisposable = timeToBar()
	}
	
	private fun hideVideoToolbar() {
		videoToolBar.visibility = GONE
		isControllerHidden = true
		mDisposable.dispose()
	}
	
	fun onBackPressed() {
		if (isLandscape()) {
			activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
			updateVideoLayoutParams()
		} else
			activity.finish()
	}
	
	override fun onConfigurationChanged(newConfig: Configuration) {
		super.onConfigurationChanged(newConfig)
		val layoutParams = mActivity.window.attributes
		if (isLandscape()) {
			layoutParams.flags = layoutParams.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
			mActivity.window.attributes = layoutParams
		} else {
			layoutParams.flags = layoutParams.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN
			mActivity.window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
		}
		updateVideoLayoutParams()
		
	}
	
	fun updateVideoLayoutParams() {
		val lp = videoContent.layoutParams
		if (isLandscape()) {
			ivFullScreen.visibility = GONE
			lp.height = UIutil.getDisplayMetrics(context).heightPixels
		} else {
			ivFullScreen.visibility = VISIBLE
			lp.height = UIutil.dp2px(context , 200)
		}
		
		videoContent.layoutParams = lp
	}
	
	fun isLandscape(): Boolean {
		return activity.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
	}
	
	fun enterRoom(room: Room?) {
		if (room != null) {
			anchorFragment.room = room
		}
		if (title.isEmpty()) tvRoomTitle.text = room?.title
		tvViewer.text = room?.getView()
		video.setVideoPath(room?.room_lines!![0].flv.streamSrc.src)
		video.displayAspectRatio = PLVideoView.ASPECT_RATIO_FIT_PARENT
		video.setOnPreparedListener {
			progressBar.visibility = View.GONE
			video.start()
		}
		
	}
	
	override fun getLayoutId(): Int {
		return R.layout.fragment_room
	}
	
	override fun newP(): RoomPresenter {
		return RoomPresenter()
	}
	
	
	override fun onResume() {
		super.onResume()
		if (video.isPlaying)
			video.start()
		view!!.isFocusableInTouchMode = true
		view!!.requestFocus()
		view!!.setOnKeyListener({ _ , keyCode , event ->
			if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_UP) {
				onBackPressed()
				return@setOnKeyListener true
			}
			return@setOnKeyListener false
		})
	}
	
	override fun onPause() {
		super.onPause()
		if (video.isPlaying)
			video.pause()
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		if (video.isPlaying)
			video.stopPlayback()
	}
}
