package com.example.yuki.yukitv

import android.app.Application

import cn.droidlover.xdroidmvp.net.NetError
import cn.droidlover.xdroidmvp.net.NetProvider
import cn.droidlover.xdroidmvp.net.RequestHandler
import cn.droidlover.xdroidmvp.net.XApi
import okhttp3.CookieJar
import okhttp3.Interceptor
import okhttp3.OkHttpClient.Builder

/**
 * 作者：Yuki  -  2017/5/24
 * 邮箱：125508663@qq.com
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        XApi.registerProvider(object : NetProvider {
            override fun configInterceptors(): Array<Interceptor?> {
                return arrayOfNulls(0)
            }

            override fun configHttps(builder: Builder) {

            }

            override fun configCookie(): CookieJar? {
                return null
            }

            override fun configHandler(): RequestHandler? {
                return null
            }

            override fun configConnectTimeoutMills(): Long {
                return 0
            }

            override fun configReadTimeoutMills(): Long {
                return 0
            }

            override fun configLogEnable(): Boolean {
                return false
            }

            override fun handleError(netError: NetError): Boolean {
                return false
            }
        })
    }
}
