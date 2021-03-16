package com.amantech.wordlist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "word_table")
data class WordEntity(
    @field:ColumnInfo(name = "word") @field:PrimaryKey val word: String
)