package com.silverknife.meizhi.mvp.model

import android.annotation.SuppressLint
import com.silvericekey.skutilslibrary.rxjava.execute
import com.silvericekey.skutilslibrary.utils.HttpUtil
import com.silverknife.meizhi.app.net.TestInterceptor
import com.silverknife.meizhi.app.Api

class LoginResponse {

    var loginType: Int = 0
    var code: Int = 0
    var isLogin: Boolean = false
    var msg: String? = null
    var userName: String? = null
    var password: String? = null

    var status: Int = 0
    var ret: Int = 0
    var data: LoginResponse? = null
    var info: LoginResponse? = null

    companion object {

        @SuppressLint("CheckResult")
        @JvmStatic
        fun getData(onSuccess: (response: LoginResponse) -> Unit, onError: (throwable: Throwable) -> Unit) {
            HttpUtil.getInstance()
                    .addInterceptor(TestInterceptor())
                    .obtainClass(Api::class.java)
                    .login("18368402184", "400938")
                    .execute({
                        onSuccess(it)
                    }, {
                        onError(it)
                    })
//                    .setSchedulers(callback)
        }
    }
}