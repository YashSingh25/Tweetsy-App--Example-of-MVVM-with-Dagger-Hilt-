package com.yash.tweetsyapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.tweetsyapp.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: TweetRepository):ViewModel() {

    val categories:StateFlow<List<String>>
    get() = repository.categories

    init {
        viewModelScope.launch {
            repository.getCategories()
        }
    }
}