package com.amantech.wordlist.database

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [WordEntity::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}

lateinit var INSTANCE: WordRoomDatabase

//To return Singleton INSTANCE of Database
fun getDatabase(context: Context): WordRoomDatabase {
    synchronized(WordRoomDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                WordRoomDatabase::class.java,
                "word_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}