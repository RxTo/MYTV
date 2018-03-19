package com.example.yuki.rxtv.mvp.model

import cn.droidlover.xdroidmvp.net.IModel

/**
 * Created by Yuki on 2017/5/17.
 */

class LiveCategory : IModel {

    var id : Int = 0
    var name : String? = null
    var is_default : Int = 0
    var sort : Int = 0
    var icon_gray : String? = null
    var icon_red : String? = null
    var icon_image : String? = null
    var slug : String = ""
    var type : Int = 0
    var screen : Int = 0

    override fun isNull() : Boolean {
        return false
    }

    override fun isAuthError() : Boolean {
        return false
    }

    override fun isBizError() : Boolean {
        return false
    }

    override fun getErrorMsg() : String? {
        return null
    }
}
