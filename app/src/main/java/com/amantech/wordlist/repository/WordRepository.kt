package com.amantech.wordlist.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.amantech.wordlist.database.WordDao
import com.amantech.wordlist.database.WordEntity
import com.amantech.wordlist.database.WordRoomDatabase


class WordRepository(val application: Application) {
    private lateinit var _mWordDao: WordDao
    private lateinit var _mAllWords: LiveData<List<WordEntity>>

    init {
        val db: WordRoomDatabase = WordRoomDatabase.getDatabase(application)!!
        _mWordDao = db.wordDao()
        _mAllWords = _mWordDao.allWords;
    }

    val allWords
        get() = _mAllWords

    fun insert(word: WordEntity) {
        insertAsyncTask(_mWordDao).execute(word)
    }

    class insertAsyncTask(val mAsyncTaskDao: WordDao) : AsyncTask<WordEntity, Void, Boolean>() {
        override fun doInBackground(vararg params: WordEntity): Boolean {
            mAsyncTaskDao.insert(params[0])
            //retrun true on success
            return true
        }
    }

}