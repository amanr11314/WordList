package com.amantech.wordlist.repository

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.amantech.wordlist.database.WordDao
import com.amantech.wordlist.database.WordEntity
import com.amantech.wordlist.database.WordRoomDatabase
import com.amantech.wordlist.database.getDatabase


class WordRepository(application: Application) {
    private var mWordDao: WordDao
    val db: WordRoomDatabase

    init {
        db = getDatabase(application)
        mWordDao = db.wordDao()
        db.close()
    }

    val mAllWords: LiveData<List<WordEntity>>
        get() {
            return mWordDao.allWords
        }


    fun insert(word: WordEntity) {
        val result = insertAsyncTask(mWordDao).execute(word).get()
    }


    class insertAsyncTask(private val mAsyncTaskDao: WordDao) :
        AsyncTask<WordEntity, Void, Boolean>() {
        override fun doInBackground(vararg params: WordEntity): Boolean {
            mAsyncTaskDao.insert(params[0])
            return true
        }
    }

}