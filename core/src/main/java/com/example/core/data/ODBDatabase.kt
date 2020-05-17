package com.example.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.vo.LCBOItem

@Database(
    entities = [LCBOItem::class],
    version = 3,
    exportSchema = false
)
abstract class ODBDatabase : RoomDatabase() {
    abstract fun lcboItemDao(): LCBOItemDao
}
