package com.salvation.proxylist.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProxyEntity::class], version = 1)
abstract class ProxyDatabase : RoomDatabase() {

    abstract fun proxyDao(): ProxyDao

    companion object{

        @Volatile
        private var noteRoomInstance: ProxyDatabase? = null

        fun getDatabase(context: Context): ProxyDatabase? {
            if (noteRoomInstance == null) {
                synchronized(ProxyDatabase::class.java) {
                    if (noteRoomInstance == null) {
                        noteRoomInstance = Room.databaseBuilder(
                            context.applicationContext,
                            ProxyDatabase::class.java, "master_database.db"
                        )
                            .build()
                    }
                }
            }
            return noteRoomInstance
        }

    }


}