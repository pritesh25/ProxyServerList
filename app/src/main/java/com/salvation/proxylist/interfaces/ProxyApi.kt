package com.salvation.proxylist.interfaces

import com.salvation.proxylist.model.ProxyModel
import retrofit2.Call
import retrofit2.http.GET

interface ProxyApi {
    @GET("proxy")
    fun postData(): Call<ProxyModel?>?
}