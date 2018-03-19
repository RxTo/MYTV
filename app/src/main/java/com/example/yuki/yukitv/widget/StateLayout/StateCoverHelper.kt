package com.example.yuki.yukitv.widget.StateLayout

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams

/**
 * Created by: Simon
 * Email: simon.han0220@gmail.com
 * Created on: 2015/10/25 13:44
 */

class StateCoverHelper(view: View) : StateViewHelper {
    override val view: View
        get() = view
    val helper: StateReplaceHelper

    init {
        val group = view.parent as ViewGroup

        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams
                .MATCH_PARENT)
        val floatView = View(view.context)
        group.addView(floatView, params)
        helper = StateReplaceHelper(floatView)
    }


    override fun showContentView() {
        helper.showContentView()
    }

    override fun switchTo(view: View?) {
        helper.switchTo(view)
    }

}
