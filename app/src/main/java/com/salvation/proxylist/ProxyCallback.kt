package com.salvation.proxylist

interface ProxyCallback {
    /*fun onIpAddressClicked(ipAddress: String?)
    fun onPortClicked(port: String?)
    fun onProtocolClicked(protocol: String?)*/
    fun onProxySelected(protocol: ArrayList<ProxyDetailModel>)
}