package com.example.yuki.yukitv.presenter

import cn.droidlover.xdroidmvp.net.NetError
import com.example.yuki.yukitv.view.fragment.BaseFragment
import com.example.yuki.rxtv.mvp.model.LiveCategory
import com.example.yuki.yukitv.net.Api
import com.example.yuki.yukitv.view.fragment.HomeFragment
import com.example.yuki.yukitv.view.fragment.LiveListFragment
import com.google.gson.JsonParseException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import java.net.UnknownHostException


class HomePresenter : BasePresenter<HomeFragment>() {
    fun getAllCategory() {
        Api.apiService.allCategories
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(v.bindToLifecycle<List<LiveCategory>>())
                .subscribe(
                        {
                            val titleList = ArrayList<String?>()
                            val listCategory = ArrayList<BaseFragment<*>>()
                            titleList.add("推荐")
                            listCategory.add(LiveListFragment.newInstance("recommend"))
                            titleList.add("全部")
                            listCategory.add(LiveListFragment.newInstance("all"))
                            for (item in it) {
                                titleList.add(item.name)
                                listCategory.add(LiveListFragment.newInstance(item.slug))
                            }
                            v.onLoadData(titleList, listCategory)
                        },
                        {
                            it.printStackTrace()
                        })
    }

    private fun getNetError(e: Throwable): NetError {

        val error: NetError
        if (e !is NetError) {
            if (e is UnknownHostException) {
                error = NetError(e, NetError.NoConnectError)
            } else if (e is JSONException || e is JsonParseException) {
                error = NetError(e, NetError.ParseError)
            } else {
                error = NetError(e, NetError.OtherError)
            }
        } else {
            error = e
        }
        return error
    }

}
