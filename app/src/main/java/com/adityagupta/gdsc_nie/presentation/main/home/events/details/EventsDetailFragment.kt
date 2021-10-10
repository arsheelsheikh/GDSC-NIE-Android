package com.adityagupta.gdsc_nie.presentation.main.home.events.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.adityagupta.gdsc_nie.R
import com.adityagupta.gdsc_nie.data.remote.EventDetailData.Response
import com.adityagupta.gdsc_nie.data.remote.EventDetailData.SpeakerData
import com.adityagupta.gdsc_nie.databinding.FragmentEventsDetailBinding
import com.adityagupta.gdsc_nie.domain.adapters.SpeakersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsDetailFragment : Fragment() {

    lateinit var binding: FragmentEventsDetailBinding
    lateinit var adapter: SpeakersAdapter
    private val viewModel by viewModels<EventDetailsViewModel>()
    val args: EventsDetailFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_events_detail, container, false)

        adapter = SpeakersAdapter()
        binding.edfRecyclerView.adapter = adapter
        getResponseUsingCoroutines()

        return binding.root
    }

    private fun getResponseUsingCoroutines() {
        viewModel.responseLiveData.observe(viewLifecycleOwner, {
            setRecyclerWithFirebaseData(it)
        })
    }

    private fun setRecyclerWithFirebaseData(response: Response): Int {
        Log.i("firebase", "firebase data")

        response.eventDetails?.let { events ->
            Log.i("firebase", "Event Size " + events.size.toString())
            Log.i("firebase", "Event Id " + events[0].id)

            events.forEach { event ->
                binding.edProgressBar.visibility = View.INVISIBLE
                binding.edMainCardView.visibility = View.VISIBLE
                if(event.id == args.event.id){
                    val speaker1 = SpeakerData(event.speaker1link, event.speaker1title)
                    val speaker2 = SpeakerData(event.speaker2link, event.speaker2title)
                    val speaker3 = SpeakerData(event.speaker3link, event.speaker3title)
                    val list = listOf<SpeakerData>(speaker1, speaker2, speaker3)
                    adapter.speakers = list
                    binding.edEventDetailsTextView.text = event.description
                    binding.edEventTitleTextView.text = args.event.title
                    binding.edEventTimingsTextView.text = args.event.duration
                    return 1
                }
            }
        }

        response.exception?.let { exception ->
            exception.message?.let {
                Log.e("firebase", it)
            }
        }
        return 1
    }

}