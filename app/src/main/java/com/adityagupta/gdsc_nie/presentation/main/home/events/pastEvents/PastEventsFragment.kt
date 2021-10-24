package com.adityagupta.gdsc_nie.presentation.main.home.events.pastEvents

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.adityagupta.gdsc_nie.R
import com.adityagupta.gdsc_nie.databinding.FragmentPastEventsBinding
import com.adityagupta.gdsc_nie.domain.adapters.PastEventsRecyclerAdapter
import com.adityagupta.gdsc_nie.data.remote.PastEventsData.RecyclerData
import com.adityagupta.gdsc_nie.data.remote.PastEventsData.Response
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class PastEventsFragment(
) : Fragment() {

    private lateinit var binding: FragmentPastEventsBinding
    private lateinit var database: FirebaseDatabase
    private val viewModel by viewModels<PastEventsViewModel>()
    private var recyclerAdapter = PastEventsRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Initiate late init values
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_past_events, container, false)
        database = Firebase.database

        getResponseUsingCoroutines()
        setUpRecyclerView()

        return binding.root
    }


    private fun setUpRecyclerView() {
        binding.peRecyclerView.adapter = recyclerAdapter
    }

    private fun getResponseUsingCoroutines() {
        viewModel.responseLiveData.observe(viewLifecycleOwner, {
            setRecyclerWithFirebaseData(it)
        })
    }

    private fun setRecyclerWithFirebaseData(response: Response) {
        response.pastEvents?.let { events ->

            var calendar = Calendar.getInstance()
            var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            var dateTime = simpleDateFormat.format(calendar.time)
            var dateCurrent = SimpleDateFormat("yyyy-MM-dd").parse(dateTime)
            var eventssssssss = mutableListOf<RecyclerData>()
            events.forEach {
                val date = SimpleDateFormat("yyyy-MM-dd").parse(it.expiry)

                if(date < dateCurrent){
                    eventssssssss.add(it)
                }

            }

            Log.i("date", dateTime.toString())
            recyclerAdapter.events = eventssssssss
            binding.peProgressBar.visibility = View.GONE
        }

        response.exception?.let { exception ->
            exception.message?.let {
                Log.e("firebase", it)
            }
        }
    }
}