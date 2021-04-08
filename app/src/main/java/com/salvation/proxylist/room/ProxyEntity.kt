package com.salvation.proxylist.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProxyTable")
data class ProxyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,

    var self: String?,
    var parent: String?,
    var key: String?,
    var hit: String?,
    var count: String?,

    var ip: String?,
    var port: Int?,
    var protocol: String?,

    var anonymity: String?,
    var lastTested: String?,
    var allowsRefererHeader: Boolean?,
    var allowsUserAgentHeader: Boolean?,
    var allowsCookies: Boolean?,
    var allowsPost: Boolean?,
    var allowsHttps: Boolean?,
    var country: String?,
    var connectTime: String?,
    var downloadSpeed: String?,
    var secondsToFirstByte: String?,
    var uptime: String?,

    val city: String?,
    val countryName: String?,
    val continentName: String?,
    val longitude: Double?,
    val latitude: Double?,
    val type: String?,
    val zip: String?


) {
    constructor(
        self: String?,
        parent: String?,
        key: String?,
        hit: String?,
        count: String?,

        ip: String?,
        port: Int?,
        protocol: String?,

        anonymity: String?,
        lastTested: String?,
        allowsRefererHeader: Boolean?,
        allowsUserAgentHeader: Boolean?,
        allowsCookies: Boolean?,
        allowsPost: Boolean?,
        allowsHttps: Boolean?,
        country: String?,
        connectTime: String?,
        downloadSpeed: String?,
        secondsToFirstByte: String?,
        uptime: String?,

        city: String?,
        countryName: String?,
        continentName: String?,
        longitude: Double?,
        latitude: Double?,
        type: String?,
        zip: String?
    ) : this(
        0,
        self,
        parent,
        key,
        hit,
        count,
        ip,
        port,
        protocol,
        anonymity,
        lastTested,
        allowsRefererHeader,
        allowsUserAgentHeader,
        allowsCookies,
        allowsPost,
        allowsHttps,
        country,
        connectTime,
        downloadSpeed,
        secondsToFirstByte,
        uptime,

        city,
        countryName,
        continentName,
        longitude,
        latitude,
        type,
        zip
    )
}