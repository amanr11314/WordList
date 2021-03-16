package com.amantech.wordlist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.amantech.wordlist.database.WordEntity
import com.amantech.wordlist.repository.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private var _mRepository: WordRepository = WordRepository(application)
    private var _mAllWords: LiveData<List<WordEntity>> = _mRepository.allWords

    val allWords: LiveData<List<WordEntity>>
        get() = _mAllWords

    fun insert(word: WordEntity) {
        _mRepository.insert(word)
    }
}