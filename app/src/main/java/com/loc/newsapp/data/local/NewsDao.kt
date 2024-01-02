package com.loc.newsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.loc.newsapp.domain.model.ArticlesItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(articlesItem: ArticlesItem)

    @Delete
    suspend fun delete(articlesItem: ArticlesItem)

    @Query("SELECT * FROM Articles")
    fun getListArticles(): Flow<List<ArticlesItem>>

    @Query("SELECT * FROM Articles Where url=:url")
    suspend fun getArticles(url: String): ArticlesItem?
}