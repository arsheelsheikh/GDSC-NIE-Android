package com.adityagupta.gdsc_nie.data.repository

import com.adityagupta.gdsc_nie.data.remote.FeaturedData.FeaturedEventData
import com.adityagupta.gdsc_nie.data.remote.FeaturedData.Response
import com.adityagupta.gdsc_nie.domain.repository.HomeFragmentRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeFragmentRepositoryImpl @Inject constructor(
    private val rootRef: DatabaseReference
) : HomeFragmentRepository{
    private val featuredRef: DatabaseReference = rootRef.child("featured")

    override suspend fun getResponseFromRealtimeDatabaseUsingCoroutines(): Response {
        val response = Response()
        try {
            response.featuredEvent = featuredRef.get().await().children.map { snapShot ->
                snapShot.getValue(FeaturedEventData::class.java)!!
            }
        } catch (exception: Exception) {
            response.exception = exception
        }
        return response
    }
}