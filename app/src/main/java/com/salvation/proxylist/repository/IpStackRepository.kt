package com.salvation.proxylist.repository

import androidx.lifecycle.MutableLiveData
import com.salvation.proxylist.BuildConfig.ACCESS_KEY_IPSTACK
import com.salvation.proxylist.interfaces.IpStackApi
import com.salvation.proxylist.model.IpStackModel
import com.salvation.proxylist.responses.IpStackResponse
import com.salvation.proxylist.utils.RetrofitClientApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IpStackRepository {

    private var mTag = "IpAddressRepository"

    private val proxyApi =
        RetrofitClientApi.getRetrofitClientIpStack().create(IpStackApi::class.java)
    private val apiResponse = MutableLiveData<IpStackResponse>()

    fun setIpStackResponse(ipAddress: String) {
        proxyApi.postData(ipAddress, ACCESS_KEY_IPSTACK)
            ?.enqueue(object : Callback<IpStackModel?> {
                override fun onResponse(
                    call: Call<IpStackModel?>,
                    response: Response<IpStackModel?>
                ) {
                    response.body()?.let {
                        apiResponse.postValue(IpStackResponse(it, response.code()))
                    } ?: kotlin.run {
                        response.errorBody()?.let {
                            apiResponse.postValue(IpStackResponse(it, response.code()))
                        }
                    }
                }

                override fun onFailure(call: Call<IpStackModel?>, t: Throwable) {
                    apiResponse.postValue(IpStackResponse(t))
                }

            })
    }

    fun getIpStackResponse(): MutableLiveData<IpStackResponse> {
        return apiResponse
    }
}