package com.salvation.proxylist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.salvation.proxylist.room.ProxyDatabase
import com.salvation.proxylist.room.ProxyEntity
import kotlinx.coroutines.launch

class ProxyViewModel(application: Application) : AndroidViewModel(application) {

    private val mTag = "FaqViewModel"

    private val proxyDatabase = ProxyDatabase.getDatabase(application)
    private val proxyDao = proxyDatabase?.proxyDao()
    var proxyList = proxyDao?.proxyList

    private var faqRepository: ProxyRepository? = null
    private var faqApiResponse: LiveData<ProxyResponse>? = null

    fun init() {
        faqRepository = ProxyRepository()
        faqApiResponse = faqRepository?.getProxyResponse()
    }

    fun setProxyRequest() {
        faqRepository?.setProxyRequest()
    }

    fun getProxyRequest(): LiveData<ProxyResponse>? {
        return faqApiResponse
    }

    /**
     * Database CRUD
     */

    fun insertProxy(model: ProxyEntity) {
        viewModelScope.launch {
            proxyDao?.insert(model)
        }
    }

}