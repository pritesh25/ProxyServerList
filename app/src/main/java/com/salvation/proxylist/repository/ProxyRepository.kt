package com.salvation.proxylist.repository

import androidx.lifecycle.MutableLiveData
import com.salvation.proxylist.interfaces.ProxyApi
import com.salvation.proxylist.model.ProxyModel
import com.salvation.proxylist.responses.ProxyResponse
import com.salvation.proxylist.utils.RetrofitClientApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProxyRepository {

        private var mTag = "ProxyRepository"

    private val proxyApi = RetrofitClientApi.getRetrofitClientProxyList().create(ProxyApi::class.java)
    private val apiResponse = MutableLiveData<ProxyResponse>()

    fun setProxyRequest() {
        proxyApi.postData()?.enqueue(object : Callback<ProxyModel?> {
            override fun onResponse(call: Call<ProxyModel?>, response: Response<ProxyModel?>) {
                response.body()?.let {
                    apiResponse.postValue(ProxyResponse(it, response.code()))
                } ?: kotlin.run {
                    response.errorBody()?.let {
                        apiResponse.postValue(ProxyResponse(it, response.code()))
                    }
                }
            }

            override fun onFailure(call: Call<ProxyModel?>, t: Throwable) {
                apiResponse.postValue(ProxyResponse(t))
            }

        })
    }

    fun getProxyResponse(): MutableLiveData<ProxyResponse> {
        return apiResponse
    }
}