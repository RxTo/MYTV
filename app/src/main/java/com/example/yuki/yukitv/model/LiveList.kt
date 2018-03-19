package com.example.yuki.yukitv.model

import cn.droidlover.xdroidmvp.net.IModel
import com.example.yuki.yukitv.utils.DecimalFormatUtil

/**
 * @author Jenly [Jenly](mailto:jenly1314@gmail.com)
 * *
 * @since 2017/3/3
 */
class LiveList : IModel {

    var icon : String? = null
    var pageCount : Int = 0
    var data : List<Live>? = null

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

    class Live {

        var no : String? = null
        var nick : String? = null
        var avatar : String? = null
        var follow : String? = null
        var uid : String? = null
        var play_at : String? = null
        var views : String? = null
        var intro : String? = null
        var videoQuality : String? = null
        var thumb : String? = null
        var stream : String? = null
        var position : String? = null
        var announcement : String? = null
        var id : String? = null
        var video : String? = null
        var slug : String? = null
        var category_id : String? = null
        var cover : String? = null
        var status : String? = null
        var weight : String? = null
        var start_time : String? = null
        var check : String? = null
        var priv : String? = null
        var category_name : String? = null
        var title : String? = null
        var last_play_at : String? = null
        var landscape : String? = null
        var view : String? = null
        get() {
            try {
                val views = Integer.parseInt(field)
                return DecimalFormatUtil.formatW(views)
            } catch (e : Exception) {

            }

            return field
        }
        var screen : Int = 0
        var categoryId : String? = null
        var hidden : Boolean = false
        var play_status : Boolean = false
        var category_slug : String? = null
        var frame : String? = null
        var lol_status : Int = 0
        var beauty_cover : String? = null
        var recommend_image : String? = null
        var love_cover : String? = null

    }
}
