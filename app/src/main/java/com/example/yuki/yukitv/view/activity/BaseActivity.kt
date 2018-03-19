package com.example.yuki.yukitv.view.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import cn.droidlover.xdroidmvp.mvp.XActivity
import cn.droidlover.xdroidmvp.mvp.XPresent
import com.example.yuki.yukitv.R.*

/**
 * 作者：Yuki  -  2017/5/16
 * 邮箱：125508663@qq.com
 */

abstract class BaseActivity : XActivity<XPresent<*>>() {
    lateinit var mToast: Toast
    lateinit var mToastRoot: View
    lateinit var mToastIcon: View


    override fun initData(p0: Bundle?) {
        mToast = Toast(this)
        val view = View.inflate(this, layout.layout_toast, null)
        mToastRoot = view.findViewById(id.toast_root)
        mToastIcon = view.findViewById(id.toast_icon)
        mToast.view = view
    }

    fun showWarningMsg(info: String?) {
        mToastRoot.setBackgroundColor(ContextCompat.getColor(this, color.toast_warning))
        mToastIcon.background = ContextCompat.getDrawable(this, drawable.ic_warning)
        mToast.setText(info)
        mToast.duration = Toast.LENGTH_SHORT
        mToast.show()
    }

    fun showErrorMsg(info: String?) {
        mToastRoot.setBackgroundColor(ContextCompat.getColor(this, color.toast_error))
        mToastIcon.background = ContextCompat.getDrawable(this, drawable.ic_error)
        mToast.setText(info)
        mToast.duration = Toast.LENGTH_SHORT
        mToast.show()
    }
}
