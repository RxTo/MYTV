package com.example.yuki.yukitv.widget.StateLayout

import android.view.View

/**
 * Created by: Simon
 * Email: simon.han0220@gmail.com
 * Created on: 2015/8/28 10:42
 */

interface StateViewHelper {
    fun showContentView()

    fun switchTo(view: View?)
    val view: View

}
