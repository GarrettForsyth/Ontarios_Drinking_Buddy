package com.example.core.data

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.core.vo.LCBOItemQueryParameters
import com.example.core_test.createTestLCBOItem
import com.example.core_test.testRules.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldContainAll
import org.amshove.kluent.shouldNotContainAny
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException

/**
 * Integration test for LCBOItemDao and ODBDatabase
 */
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@MediumTest
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class LCBOItemDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineScope = MainCoroutineScopeRule()

    private lateinit var db: ODBDatabase
    private lateinit var lcboItemDao: LCBOItemDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            ODBDatabase::class.java
        ).allowMainThreadQueries().build()
        lcboItemDao = db.lcboItemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() = db.close()

    @Test
    @Throws(IOException::class)
    fun `insert lcbo item list and get items by query`() = coroutineScope.runBlockingTest {
        // GIVEN - a list of lcbo items
        val lcboItems = listOf(
            createTestLCBOItem(1).apply { name = "beer" },
            createTestLCBOItem(4).apply { name = "wine" },
            createTestLCBOItem(8).apply { name = "good beer" },
            createTestLCBOItem(9).apply { primaryCategory = "beer" },
            createTestLCBOItem(10).apply { secondaryCategory = "beer" },
            createTestLCBOItem(11).apply { tertiaryCategory = "beer" },
            createTestLCBOItem(12).apply { tags = "beer other tags" },
            createTestLCBOItem(13).apply { tags = "wine red france" },
            createTestLCBOItem(14).apply { tags = "bud beer light" },
            createTestLCBOItem(15).apply { primaryCategory = "Beer" }
        )

        // WHEN - it is inserted into the database
        lcboItemDao.insert(lcboItems)

        // AND - items are queried with a filter string 'beer'
        val queryParameters = LCBOItemQueryParameters(filterString = "beer")
        val sql = SupportSQLiteQueryConverter().convert(queryParameters)

        lcboItemDao.getLcboItems(sql).take(1).collect {
            // THEN - only items matching the filter string 'beer' in
            // their name, primaryCategory, SecondaryCategory, tertiaryCategory
            // or tag fields are returned
            it.shouldContainAll(
                listOf(
                    lcboItems[0],
                    lcboItems[2],
                    lcboItems[3],
                    lcboItems[4],
                    lcboItems[5],
                    lcboItems[6],
                    lcboItems[8],
                    lcboItems[9]
                )
            )
            it.shouldNotContainAny(listOf(lcboItems[1], lcboItems[7]))
        }
    }
}

