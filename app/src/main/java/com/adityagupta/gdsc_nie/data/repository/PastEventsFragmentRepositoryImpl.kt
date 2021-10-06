package com.adityagupta.gdsc_nie.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.adityagupta.gdsc_nie.data.remote.PastEventsData.FirebaseCallback
import com.adityagupta.gdsc_nie.data.remote.PastEventsData.RecyclerData
import com.adityagupta.gdsc_nie.data.remote.PastEventsData.Response
import com.adityagupta.gdsc_nie.domain.repository.PastEventsRepository
import com.google.firebase.database.*
import kotlinx.coroutines.tasks.await

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class PastEventsFragmentRepositoryImpl @Inject constructor(
    private val rootRef: DatabaseReference
) : PastEventsRepository{

    private val pastEventsRef: DatabaseReference = rootRef.child("pastEvents")

    override fun getResponseFromRealtimeDatabaseUsingCallback(callback: FirebaseCallback) {
        pastEventsRef.get().addOnCompleteListener { task ->
            val response = Response()
            if (task.isSuccessful) {
                val result = task.result
                result?.let {
                    response.pastEvents = result.children.map { snapShot ->
                        snapShot.getValue(RecyclerData::class.java)!!
                    }
                }
            } else {
                response.exception = task.exception
            }
            callback.onResponse(response)
        }
    }

    override fun getResponseFromRealtimeDatabaseUsingLiveData() : MutableLiveData<Response> {
        val mutableLiveData = MutableLiveData<Response>()
        pastEventsRef.get().addOnCompleteListener { task ->
            val response = Response()
            if (task.isSuccessful) {
                val result = task.result
                result?.let {
                    response.pastEvents = result.children.map { snapShot ->
                        snapShot.getValue(RecyclerData::class.java)!!
                    }
                }
            } else {
                response.exception = task.exception
            }
            mutableLiveData.value = response
        }
        return mutableLiveData
    }

    override suspend fun getResponseFromRealtimeDatabaseUsingCoroutines(): Response {
        val response = Response()
        try {
            response.pastEvents = pastEventsRef.get().await().children.map { snapShot ->
                snapShot.getValue(RecyclerData::class.java)!!
            }
        } catch (exception: Exception) {
            response.exception = exception
        }
        return response
    }
}