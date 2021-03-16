package com.amantech.wordlist.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao


    companion object {
        lateinit var INSTANCE: WordRoomDatabase
        fun getDatabase(context: Context): WordRoomDatabase {
            synchronized(WordRoomDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WordRoomDatabase::class.java,
                        "word_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(sRoomDatabaseCallback)
                        .build()
                }
            }
            return INSTANCE
        }

        private val sRoomDatabaseCallback: Callback = object : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                val result = PopulateDbAsync(INSTANCE).execute().get()
                if (result) print("SUCCESS")
            }
        }
    }


    class PopulateDbAsync(private val db: WordRoomDatabase) :
        AsyncTask<Void, Void, Boolean>() {
        private var mDao: WordDao = db.wordDao()
        var words = listOf("dolphin", "crocodile", "cobra")
        override fun doInBackground(vararg params: Void?): Boolean {
            mDao.deleteAll()
            for (element in words) {
                val word = WordEntity(element)
                mDao.insert(word)
            }
            return true
        }

    }
}