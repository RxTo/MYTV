package com.example.yuki.yukitv.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy.ALL
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.yuki.yukitv.R
import com.example.yuki.yukitv.R.id
import com.example.yuki.yukitv.model.Recommend.RoomBean.ListBean


class RecommendItemAdapter(layoutResId : Int, data : List<ListBean>?, val isShow : Boolean) : BaseQuickAdapter<ListBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper : BaseViewHolder, item : ListBean) {
        helper.run {
            setText(id.tvTitle, item.title)
            setText(id.tvName, item.nick)
            setText(id.tvViewer, item.view)
            if (isShow)setText(id.tvLocation, item.position)

        }
        Glide.with(mContext).load(item.thumb).dontAnimate().diskCacheStrategy(ALL).placeholder(R.drawable.live_default).into(helper.getView(R.id.ivThumb))
    
//        ILFactory.getLoader().loadNet(helper.getView(id.ivThumb), item.thumb, Options(drawable.live_default, drawable.live_default).scaleType(CENTER_CROP))

    }
}
