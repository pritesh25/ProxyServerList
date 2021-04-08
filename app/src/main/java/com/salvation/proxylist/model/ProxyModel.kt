package com.salvation.proxylist.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ProxyModel(
    @SerializedName("allowsCookies") var allowsCookies: Boolean?,
    @SerializedName("allowsCustomHeaders") var allowsCustomHeaders: Boolean?,
    @SerializedName("allowsHttps") var allowsHttps: Boolean?,
    @SerializedName("allowsPost") var allowsPost: Boolean?,
    @SerializedName("allowsRefererHeader") var allowsRefererHeader: Boolean?,
    @SerializedName("allowsUserAgentHeader") var allowsUserAgentHeader: Boolean?,
    @SerializedName("anonymity") var anonymity: String?,
    @SerializedName("cache") var cache: Cache?,
    @SerializedName("connectTime") var connectTime: String?,
    @SerializedName("country") var country: String?,
    @SerializedName("downloadSpeed") var downloadSpeed: String?,
    @SerializedName("ip") var ip: String?,
    @SerializedName("lastTested") var lastTested: String?,
    @SerializedName("_links") var links: Links?,
    @SerializedName("port") var port: Int?,
    @SerializedName("protocol") var protocol: String?,
    @SerializedName("secondsToFirstByte") var secondsToFirstByte: String?,
    @SerializedName("stats") var stats: Stats?,
    @SerializedName("uptime") var uptime: String?
) {
    @Keep
    data class Cache(
        @SerializedName("hit") var hit: String?,
        @SerializedName("key") var key: String?
    )

    @Keep
    data class Links(
        @SerializedName("_parent") var parent: String?,
        @SerializedName("_self") var self: String?
    )

    @Keep
    data class Stats(
        @SerializedName("count") var count: String?
    )
}