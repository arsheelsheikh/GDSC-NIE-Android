package com.adityagupta.gdsc_nie.domain.repository

import androidx.lifecycle.MutableLiveData
import com.adityagupta.gdsc_nie.data.remote.FeaturedData.Response

interface HomeFragmentRepository {

    suspend fun getResponseFromRealtimeDatabaseUsingCoroutines(): Response

}