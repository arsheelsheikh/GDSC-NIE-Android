package com.adityagupta.gdsc_nie.data.repository

import com.adityagupta.gdsc_nie.data.remote.EventDetailData.EventDetailsData
import com.adityagupta.gdsc_nie.data.remote.EventDetailData.Response
import com.adityagupta.gdsc_nie.domain.repository.EventDetailsRepository
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class EventDetailsRepositoryImpl @Inject constructor(
    private val rootRef: DatabaseReference
) : EventDetailsRepository {

    private val eventDetailsRef: DatabaseReference = rootRef.child("eventDetails")

    override suspend fun getResponseFromRealtimeDatabaseUsingCoroutines(): Response {
        val response = Response()
        try {
            response.eventDetails = eventDetailsRef.get().await().children.map { snapShot ->
                snapShot.getValue(EventDetailsData::class.java)!!
            }
        } catch (exception: Exception) {
            response.exception = exception
        }
        return response
    }

}