package com.example.yuki.yukitv.model

import cn.droidlover.xdroidmvp.net.IModel
import com.example.yuki.yukitv.utils.DecimalFormatUtil


/**
 * 项目：YukiTv
 * 作者：Yuki  -  2017/5/17
 * 邮箱：125508663@qq.com
 */

class Recommend : IModel {

    var room : MutableList<RoomBean>? = null
    var ad : List<*>? = null

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

    class RoomBean {
        var id : Int = 0
        var name : String? = null
        var is_default : Int = 0
        var icon : String? = null
        var slug : String? = null
        var category_more : String? = null
        var type : Int = 0
        var screen : Int = 0
        var list : List<ListBean>? = null

        class ListBean {
            var beauty_cover : String? = null
            var width : Int = 0
            var height : Int = 0
            var no : Int = 0
            var first_play_at : String? = null
            var category_name : String? = null
            var gender : Int = 0
            var thumb : String? = null
            var last_play_at : String? = null
            var screen : Int = 0
            var video : String? = null
            var title : String? = null
            var recommend_image : String? = null
            var is_shield : Boolean = false
            var nick : String? = null
            var uid : String? = null
            var view : String? = null
                get() {
                    try {
                        val views = Integer.parseInt(field)
                        return DecimalFormatUtil.formatW(views)
                    } catch (e : Exception) {

                    }

                    return field
                }
            var category_id : Int = 0
            var stream : String? = null
            var slug : Any? = null
            var love_cover : String? = null
            var level : Int = 0
            var like : Int = 0
            var video_quality : String? = null
            var weight : Int = 0
            var starlight : Int = 0
            var check : Boolean = false
            var avatar : String? = null
            var follow : Int = 0
            var play_count : Int = 0
            var play_true : Int = 0
            var fans : Int = 0
            var max_view : Int = 0
            var default_image : String? = null
            var last_end_at : String? = null
            var position : String? = null
            var create_at : String? = null
            var last_thumb : String? = null
            var landscape : Int = 0
            var category_slug : String? = null
            var anniversary : Int = 0
            var play_status : Boolean = false
            var status : Int = 0
            var coin : Int = 0
            var frame : String? = null
            var link : String? = null
            var icontext : String? = null
            var app_shuffling_image : String? = null

        }
    }
}
