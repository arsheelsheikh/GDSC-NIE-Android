package com.adityagupta.gdsc_nie.domain.repository

import androidx.lifecycle.MutableLiveData
import com.adityagupta.gdsc_nie.data.remote.PastEventsData.FirebaseCallback
import com.adityagupta.gdsc_nie.data.remote.PastEventsData.RecyclerData
import com.adityagupta.gdsc_nie.data.remote.PastEventsData.Response

interface PastEventsRepository {

    fun getResponseFromRealtimeDatabaseUsingCallback(callback: FirebaseCallback)

    fun getResponseFromRealtimeDatabaseUsingLiveData() : MutableLiveData<Response>

    suspend fun getResponseFromRealtimeDatabaseUsingCoroutines(): Response
}