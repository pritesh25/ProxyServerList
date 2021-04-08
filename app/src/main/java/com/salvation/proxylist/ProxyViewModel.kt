package com.salvation.proxylist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.salvation.proxylist.model.IpStackModel
import com.salvation.proxylist.model.ProxyModel
import com.salvation.proxylist.repository.IpStackRepository
import com.salvation.proxylist.repository.ProxyRepository
import com.salvation.proxylist.responses.IpStackResponse
import com.salvation.proxylist.responses.ProxyResponse
import com.salvation.proxylist.room.ProxyDatabase
import com.salvation.proxylist.room.ProxyEntity
import kotlinx.coroutines.launch

class ProxyViewModel(application: Application) : AndroidViewModel(application) {

    private val mTag = "ProxyViewModel"

    private var proxyRepository: ProxyRepository? = null
    private var proxyApiResponse: LiveData<ProxyResponse>? = null

    private var ipStackRepository: IpStackRepository? = null
    private var ipStackResponse: LiveData<IpStackResponse>? = null

    fun init() {
        proxyRepository = ProxyRepository()
        ipStackRepository = IpStackRepository()

        proxyApiResponse = proxyRepository?.getProxyResponse()
        ipStackResponse = ipStackRepository?.getIpStackResponse()
    }

    /**
     * Proxy
     */
    fun setProxyRequest() {
        proxyRepository?.setProxyRequest()
    }

    fun getProxyRequest(): LiveData<ProxyResponse>? {
        return proxyApiResponse
    }

    /**
     * IpStack
     */
    fun setIpStackResponse(ipAddress: String) {
        ipStackRepository?.setIpStackResponse(ipAddress)
    }

    fun getIpStackResponse(): LiveData<IpStackResponse>? {
        return ipStackResponse
    }

    /**
     * Database CRUD
     */

    private val proxyDatabase = ProxyDatabase.getDatabase(application)
    private val proxyDao = proxyDatabase?.proxyDao()
    var proxyList = proxyDao?.proxyList

    fun insertProxy(model: ProxyModel, ipStackModel: IpStackModel) {
        viewModelScope.launch {
            proxyDao?.insert(
                ProxyEntity(
                    model.links?.self,
                    model.links?.parent,
                    model.cache?.key,
                    model.cache?.hit,
                    model.stats?.count,
                    model.ip,
                    model.port,
                    model.protocol,
                    model.anonymity,
                    model.lastTested,
                    model.allowsRefererHeader,
                    model.allowsUserAgentHeader,
                    model.allowsCookies,
                    model.allowsPost,
                    model.allowsHttps,
                    model.country,
                    model.connectTime,
                    model.downloadSpeed,
                    model.secondsToFirstByte,
                    model.uptime,

                    ipStackModel.city,
                    ipStackModel.countryName,
                    ipStackModel.continentName,
                    ipStackModel.longitude,
                    ipStackModel.latitude,
                    ipStackModel.type,
                    ipStackModel.zip

                )
            )
        }
    }

}