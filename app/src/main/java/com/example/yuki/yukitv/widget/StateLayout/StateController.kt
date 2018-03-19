package com.example.yuki.yukitv.widget.StateLayout

import android.content.Context
import android.view.LayoutInflater
import android.view.View


class StateController(val helper: StateViewHelper) {
    val context: Context = helper.view.context

    fun showErrorView(layoutResId: Int, onClickListener: View.OnClickListener) {
        val view = LayoutInflater.from(context).inflate(layoutResId, null)
        this.showErrorView(view, onClickListener)
    }

    fun showErrorView(view: View?, onClickListener: View.OnClickListener) {
        view?.setOnClickListener(onClickListener)
        helper.switchTo(view)
    }

    fun showLoadView(layoutResId: Int) {
        val view = LayoutInflater.from(context).inflate(layoutResId, null)
        this.showLoadView(view)
    }

    fun showLoadView(view: View?) {
        helper.switchTo(view)
    }

    fun showContentView() {
        helper.showContentView()
    }
}
