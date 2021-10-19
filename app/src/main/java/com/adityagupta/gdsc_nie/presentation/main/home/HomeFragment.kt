package com.adityagupta.gdsc_nie.presentation.main.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.adityagupta.gdsc_nie.R
import com.adityagupta.gdsc_nie.data.remote.FeaturedData.Response
import com.adityagupta.gdsc_nie.databinding.FragmentHomeBinding
import com.adityagupta.gdsc_nie.domain.adapters.EventsPagerAdapter
import com.adityagupta.gdsc_nie.presentation.main.home.events.NoEventsFragment
import com.adityagupta.gdsc_nie.presentation.main.home.events.UpcomingEventsFragment
import com.adityagupta.gdsc_nie.presentation.main.home.events.pastEvents.PastEventsFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var demoCollectionAdapter: DemoCollectionAdapter
    private lateinit var viewPager: ViewPager2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

/*
        setUpViewPager()
*/
        getResponseUsingCoroutines()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        demoCollectionAdapter = DemoCollectionAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = demoCollectionAdapter

        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            if(position == 0){
                tab.text = "Upcoming"
            }else{
                tab.text = "Past Events"
            }
        }.attach()

    }



    /*private fun setUpViewPager() {
        Log.i("info", "set up viewpager triggered")
        var adapter = EventsPagerAdapter(parentFragmentManager)

        adapter.addFragment(NoEventsFragment(), "Upcoming")
        adapter.addFragment(PastEventsFragment(), "Past Events")

        binding.pager.adapter = adapter
    }*/

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

    class DemoCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            // Return a NEW fragment instance in createFragment(int)
            return when(position){
                0 -> NoEventsFragment()
                1 -> PastEventsFragment()
                else -> NoEventsFragment()
            }
        }
    }





}

