package com.example.yuki.yukitv.view.fragment

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy.ALL
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.model.Room
import com.example.yuki.yukitv.presenter.ShowPresenter
import com.pili.pldroid.player.widget.PLVideoView
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_show.*

/**
 * 项目：YukiTv
 * 作者：Yuki  -  2017/5/25
 * 邮箱：125508663@qq.com
 */
class ShowFragment(val uid: String , val url: String ): BaseFragment<ShowPresenter>() {
	
	fun start() {
		if (video != null)
			video.start()
	}
	
	fun pause() {
		if (video != null)
			video.pause()
	}
	
	fun stopPlayback() {
		if (video != null)
			video.stopPlayback()
		
	}
	
	override fun onResume() {
		super.onResume()
		start()
	}
	
	override fun onPause() {
		super.onPause()
		pause()
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		stopPlayback()
	}
	
	companion object {
		fun newInstance(uid: String , url: String): ShowFragment {
			return ShowFragment(uid , url )
		}
	}
	
	
	override fun initData(p0: Bundle?) {
		super.initData(p0)
		Glide.with(activity).load(url).diskCacheStrategy(ALL).bitmapTransform(BlurTransformation(activity , 20 , 5)).dontAnimate().into(ivCover)
		video.setCoverView(ivCover)
		p.enterRoom(uid)
		initEvent()
	}
	
	
	private fun initEvent() {
		ivBack.setOnClickListener {
			activity.finish()
		}
	}
	
	fun enterRoom(room: Room?) {
		video.setVideoPath(room?.room_lines!![0].flv.streamSrc.src)
		Glide.with(activity).load(room.avatar).dontAnimate().into(civAvatar)
		rlAnchorInfo.visibility = View.VISIBLE
		tvFans.text = String.format("粉丝 %d" , room.follow)
		tvName.text = room.nick
		video.displayAspectRatio = PLVideoView.ASPECT_RATIO_PAVED_PARENT
		start()
	}
	
	override fun getLayoutId(): Int {
		return R.layout.fragment_show
	}
	
	override fun newP(): ShowPresenter? {
		return ShowPresenter()
	}
	
	
}
