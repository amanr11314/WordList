package com.amantech.wordlist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface WordDao {
    @Insert
    fun insert(word: WordEntity)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @get:Query("SELECT * from word_table ORDER BY word ASC")
    val allWords: LiveData<List<WordEntity>>
}