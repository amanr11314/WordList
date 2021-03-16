package com.amantech.wordlist.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.amantech.wordlist.database.WordDao
import com.amantech.wordlist.database.WordEntity
import com.amantech.wordlist.database.WordRoomDatabase


class WordRepository(application: Application) {
    private val mWordDao: WordDao
    val mAllWords: LiveData<List<WordEntity>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        mWordDao = db.wordDao()
        mAllWords = mWordDao.allWords;
    }

    fun insert(word: WordEntity) {
        insertAsyncTask(mWordDao).execute(word)
    }

    class insertAsyncTask(val mAsyncTaskDao: WordDao) : AsyncTask<WordEntity, Void, Boolean>() {
        override fun doInBackground(vararg params: WordEntity): Boolean {
            mAsyncTaskDao.insert(params[0])
            return true
        }
    }

}