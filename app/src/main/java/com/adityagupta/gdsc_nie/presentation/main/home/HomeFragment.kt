package com.adityagupta.gdsc_nie.presentation.main.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.adityagupta.gdsc_nie.R
import com.adityagupta.gdsc_nie.data.remote.FeaturedData.Response
import com.adityagupta.gdsc_nie.databinding.FragmentHomeBinding
import com.adityagupta.gdsc_nie.domain.adapters.EventsPagerAdapter
import com.adityagupta.gdsc_nie.presentation.main.home.events.NoEventsFragment
import com.adityagupta.gdsc_nie.presentation.main.home.events.pastEvents.PastEventsFragment
import com.adityagupta.gdsc_nie.presentation.main.home.events.pastEvents.PastEventsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        setUpViewPager()
        getResponseUsingCoroutines()
        setUpNoEventsFragment()
        binding.tabLayout.setupWithViewPager(binding.pager)


        return binding.root
    }

    private fun setUpNoEventsFragment() {

    }

    private fun setUpViewPager() {
        var adapter = EventsPagerAdapter(parentFragmentManager)

        adapter.addFragment(NoEventsFragment(), "Upcoming")
        adapter.addFragment(PastEventsFragment(), "Past Events")

        binding.pager.adapter = adapter
    }

    private fun getResponseUsingCoroutines() {
        viewModel.responseLiveData.observe(viewLifecycleOwner, {
            setRecyclerWithFirebaseData(it)
        })
    }

    private fun setRecyclerWithFirebaseData(response: Response) {
        response.featuredEvent?.let { events ->
            binding.hfProgressBar.visibility = View.GONE
            binding.hfFeaturedConstraintLayout.visibility = View.VISIBLE
            binding.hfFeaturedEventTitle.text = events[0].title
            binding.hfFeaturedEventDuration.text = events[0].timings
        }

        response.exception?.let { exception ->
            exception.message?.let {
                Log.e("firebase", it)
            }
        }
    }

}

