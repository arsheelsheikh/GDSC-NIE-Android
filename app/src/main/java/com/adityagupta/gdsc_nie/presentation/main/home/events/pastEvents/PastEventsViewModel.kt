package com.adityagupta.gdsc_nie.presentation.main.home.events.pastEvents

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.adityagupta.gdsc_nie.data.remote.PastEventsData.FirebaseCallback
import com.adityagupta.gdsc_nie.data.remote.PastEventsData.Response
import com.adityagupta.gdsc_nie.domain.repository.PastEventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PastEventsViewModel @Inject constructor(
    private val repository: PastEventsRepository
) : ViewModel() {

    fun getResponseUsingCallback(callback: FirebaseCallback) {
        repository.getResponseFromRealtimeDatabaseUsingCallback(callback)
    }

    fun getResponseUsingLiveData() : LiveData<Response> {
        return repository.getResponseFromRealtimeDatabaseUsingLiveData()
    }

    val responseLiveData = liveData(Dispatchers.IO) {
        emit(repository.getResponseFromRealtimeDatabaseUsingCoroutines())
    }
}