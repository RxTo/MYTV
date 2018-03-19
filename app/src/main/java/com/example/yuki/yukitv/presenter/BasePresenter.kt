package com.example.yuki.yukitv.presenter

import cn.droidlover.xdroidmvp.mvp.IView
import cn.droidlover.xdroidmvp.mvp.XPresent
import cn.droidlover.xdroidmvp.net.NetError

/**
 * Created by Yuki on 2017/5/21.
 */

open class BasePresenter<V : IView<*>> : XPresent<V>() {
    protected fun getErrorMsg(error: NetError): String {
        val errorMsg: String
        when (error.type) {
            NetError.ParseError -> errorMsg = "数据解析异常"

            NetError.AuthError -> errorMsg = "身份验证异常"

            NetError.BusinessError -> errorMsg = "业务异常"

            NetError.NoConnectError -> errorMsg = "网络异常"

            NetError.NoDataError -> errorMsg = "数据为空"
            else -> errorMsg = "未知异常"
        }
        return errorMsg
    }
}
