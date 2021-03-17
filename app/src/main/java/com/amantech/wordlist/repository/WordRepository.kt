package com.amantech.wordlist.repository

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.amantech.wordlist.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WordRepository(application: Application) {
    private var mWordDao: WordDao

    val db: WordRoomDatabase

    init {
        db = WordRoomDatabase.getDatabase(application)
        mWordDao = db.wordDao()
        db.close()
    }

    suspend fun insertWord(wordEntity: WordEntity) = mWordDao.insert(wordEntity)

    suspend fun deleteAllWords() = mWordDao.deleteAll()

    val mAllWords: LiveData<List<WordEntity>>
        get() {
            return mWordDao.allWords
        }

}