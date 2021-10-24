package com.adityagupta.gdsc_nie.presentation.main.home.events.upcomingEvents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.adityagupta.gdsc_nie.R
import com.adityagupta.gdsc_nie.data.remote.PastEventsData.RecyclerData
import com.adityagupta.gdsc_nie.data.remote.PastEventsData.Response
import com.adityagupta.gdsc_nie.databinding.FragmentNoEventsBinding
import com.adityagupta.gdsc_nie.domain.adapters.PastEventsRecyclerAdapter
import com.adityagupta.gdsc_nie.presentation.main.home.events.pastEvents.PastEventsViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class UpcomingEventsFragment : Fragment() {

    lateinit var binding: FragmentNoEventsBinding
    private lateinit var database: FirebaseDatabase
    private val viewModel by viewModels<PastEventsViewModel>()
    private var recyclerAdapter = PastEventsRecyclerAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_no_events, container, false)

        database = Firebase.database

        getResponseUsingCoroutines()
        setUpRecyclerView()
        setUpVisitButton()
        return binding.root
    }

    private fun setUpVisitButton() {
        binding.neVisitGDSCWebsiteButton.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://gdsc.community.dev/")
                )
            )
        }
    }

    private fun setUpRecyclerView() {
        binding.ueRecyclerView.adapter = recyclerAdapter
    }

    private fun getResponseUsingCoroutines() {
        viewModel.responseLiveData.observe(viewLifecycleOwner, {
            setRecyclerWithFirebaseData(it)
        })
    }

    private fun setRecyclerWithFirebaseData(response: Response) {
        response.pastEvents?.let { events ->

            val calendar = Calendar.getInstance()
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            val dateCurrent = SimpleDateFormat("yyyy-MM-dd").parse(simpleDateFormat.format(calendar.time))
            val filteredEvents = mutableListOf<RecyclerData>()
            events.forEach {
                val date = SimpleDateFormat("yyyy-MM-dd").parse(it.expiry)

                if(date >= dateCurrent){
                    filteredEvents.add(it)
                }

            }
            Log.i("upcoming", filteredEvents.size.toString())
            if(filteredEvents.size == 0){
                binding.neProgressBar.visibility = View.GONE
                binding.ueRecyclerView.visibility = View.GONE
                binding.noEventsView.visibility = View.VISIBLE
            }else{
                recyclerAdapter.events = filteredEvents
                binding.neProgressBar.visibility = View.GONE
            }

        }

        response.exception?.let { exception ->
            exception.message?.let {
                Log.e("firebase", it)
            }
        }
    }


}