package com.example.yuki.yukitv.widget.StateLayout

import android.view.View
import android.view.ViewGroup

/**
 * Created by: Simon
 * Email: simon.han0220@gmail.com
 * Created on: 2015/10/25 13:38
 */

class StateReplaceHelper(internal var contentView: View): StateViewHelper {
	override val view: View
		get() = contentView
	var viewGroup: ViewGroup
	var viewIndex: Int = 0
	var layoutParams: ViewGroup.LayoutParams = contentView.layoutParams
	
	init {
		if (contentView.parent != null) {
			viewGroup = contentView.parent as ViewGroup
		} else {
			viewGroup = contentView.rootView.findViewById(android.R.id.content) as ViewGroup
		}
		val count = viewGroup.childCount
		for (index in 0..count - 1) {
			if (contentView === viewGroup.getChildAt(index)) {
				viewIndex = index
				break
			}
		}
	}
	
	
	
	override fun showContentView() {
		switchTo(contentView)
	}
	
	override fun switchTo(view: View?) {
		// 如果已经是那个view，那就不需要再进行替换操作了
		if (viewGroup.getChildAt(viewIndex) !== view) {
			val parent: ViewGroup? = view?.parent as ViewGroup?
			parent?.removeView(view)
			viewGroup.removeViewAt(viewIndex)
			viewGroup.addView(view, viewIndex, layoutParams)
		}
	}
	
}
