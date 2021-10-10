package com.adityagupta.gdsc_nie.presentation.main.home.events.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.adityagupta.gdsc_nie.domain.repository.EventDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    private val repository: EventDetailsRepository
) : ViewModel() {

    val responseLiveData = liveData(Dispatchers.IO) {
        emit(repository.getResponseFromRealtimeDatabaseUsingCoroutines())
    }
}