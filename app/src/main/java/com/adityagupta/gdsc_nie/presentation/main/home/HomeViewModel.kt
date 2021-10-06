package com.adityagupta.gdsc_nie.presentation.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.adityagupta.gdsc_nie.domain.repository.HomeFragmentRepository
import com.adityagupta.gdsc_nie.domain.repository.PastEventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeFragmentRepository
) : ViewModel() {

    val responseLiveData = liveData(Dispatchers.IO) {
        emit(repository.getResponseFromRealtimeDatabaseUsingCoroutines())
    }

}