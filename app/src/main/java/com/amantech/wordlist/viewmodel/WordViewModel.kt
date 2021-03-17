package com.amantech.wordlist.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amantech.wordlist.database.WordEntity
import com.amantech.wordlist.repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : ViewModel() {

    private val mRepository: WordRepository = WordRepository(application)
    val allWords: LiveData<List<WordEntity>>
        get() = mRepository.mAllWords


    fun insert(word: WordEntity) {
        //use of coroutine scope from viewModelScope
        viewModelScope.launch {
            mRepository.insertWord(word)
        }
    }

    fun deleteAll() {
        //use of coroutine scope from viewModelScope
        viewModelScope.launch {
            mRepository.deleteAllWords()
        }
    }

}
