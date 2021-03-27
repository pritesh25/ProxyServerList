package com.salvation.proxylist.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProxyDao {
    @get:Query("SELECT * FROM ProxyTable")
    val proxyList: LiveData<List<ProxyEntity>>

    @Insert
    suspend fun insert(users: ProxyEntity)

    @Insert
    suspend fun insertAll(users: List<ProxyEntity>)

    @Delete
    suspend fun delete(proxyEntity: ProxyEntity)
}