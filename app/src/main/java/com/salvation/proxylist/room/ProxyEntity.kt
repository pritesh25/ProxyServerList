package com.salvation.proxylist.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProxyTable")
data class ProxyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var ip: String?,
    var port: Int?,
    var protocol: String?
) {
    constructor(ip: String?, port: Int?, protocol: String?) : this(0, ip, port, protocol)
}