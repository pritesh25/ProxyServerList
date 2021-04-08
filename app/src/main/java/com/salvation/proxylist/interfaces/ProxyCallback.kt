package com.salvation.proxylist.interfaces

import com.salvation.proxylist.model.ProxyDetailModel

interface ProxyCallback {
    /*fun onIpAddressClicked(ipAddress: String?)
    fun onPortClicked(port: String?)
    fun onProtocolClicked(protocol: String?)*/
    fun onProxySelected(protocol: ArrayList<ProxyDetailModel>)
}