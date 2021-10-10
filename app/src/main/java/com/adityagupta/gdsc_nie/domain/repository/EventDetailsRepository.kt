package com.adityagupta.gdsc_nie.domain.repository

import com.adityagupta.gdsc_nie.data.remote.EventDetailData.Response


interface EventDetailsRepository {

    suspend fun getResponseFromRealtimeDatabaseUsingCoroutines(): Response
}