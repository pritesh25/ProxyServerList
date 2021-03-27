package com.salvation.proxylist

import retrofit2.Call
import retrofit2.http.GET

interface ProxyApi {
    @GET("proxy")
    fun postData(): Call<ProxyModel?>?
}