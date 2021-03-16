package com.amantech.wordlist.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amantech.wordlist.database.WordEntity
import com.amantech.wordlist.repository.WordRepository

class WordViewModel(application: Application) : ViewModel() {

    private val mRepository: WordRepository = WordRepository(application)
    val allWords: LiveData<List<WordEntity>> = mRepository.mAllWords

    fun insert(word: String) {
        val newWord = WordEntity(word)
        mRepository.insert(newWord)
    }

}