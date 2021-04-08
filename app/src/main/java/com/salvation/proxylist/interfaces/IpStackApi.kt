package com.salvation.proxylist.interfaces

import com.salvation.proxylist.model.IpStackModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IpStackApi {
    @GET("{ip}")
    fun postData(
        @Path("ip") ip: String,
        @Query("access_key") accessKey: String,
    ): Call<IpStackModel?>?
}