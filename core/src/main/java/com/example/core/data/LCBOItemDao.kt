package com.example.core.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.core.vo.LCBOItem
import kotlinx.coroutines.flow.Flow

@Dao
interface LCBOItemDao {

    @RawQuery(observedEntities = [LCBOItem::class])
    fun getLcboItems(query: SupportSQLiteQuery): Flow<List<LCBOItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<LCBOItem>)
}
