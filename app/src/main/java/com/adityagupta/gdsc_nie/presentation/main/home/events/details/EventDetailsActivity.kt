package com.adityagupta.gdsc_nie.presentation.main.home.events.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.adityagupta.gdsc_nie.R
import com.adityagupta.gdsc_nie.data.remote.EventDetailData.Response
import com.adityagupta.gdsc_nie.data.remote.EventDetailData.SpeakerData
import com.adityagupta.gdsc_nie.databinding.ActivityEventDetailsBinding
import com.adityagupta.gdsc_nie.databinding.FragmentEventsDetailBinding
import com.adityagupta.gdsc_nie.domain.adapters.SpeakersAdapter
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import android.net.Uri
import com.adityagupta.gdsc_nie.data.remote.PastEventsData.RecyclerData


@AndroidEntryPoint
class EventDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEventDetailsBinding
    lateinit var adapter: SpeakersAdapter
    private val viewModel by viewModels<EventDetailsViewModel>()
    lateinit var eventArg: RecyclerData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_details)
        adapter = SpeakersAdapter(this)
        binding.edfRecyclerView.adapter = adapter

        val intent = this.intent
        eventArg = intent.extras?.get("event") as RecyclerData

        setImage()
        getResponseUsingCoroutines()

    }

    private fun setImage() {
        var imageType = eventArg.type

        binding.edEventTypeImageView.setImageResource(getImageId(imageType!!))
    }

    private fun getResponseUsingCoroutines() {
        viewModel.responseLiveData.observe(this,  {
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
                if(event.id == eventArg.id){
                    val speaker1 = SpeakerData(event.speaker1link, event.speaker1title)
                    val speaker2 = SpeakerData(event.speaker2link, event.speaker2title)
                    val speaker3 = SpeakerData(event.speaker3link, event.speaker3title)
                    val list = listOf<SpeakerData>(speaker1, speaker2, speaker3)
                    adapter.speakers = list
                    binding.edEventDetailsTextView.text = event.description
                    binding.edEventTitleTextView.text = eventArg.title
                    binding.edEventTimingsTextView.text = eventArg.duration
                    setKnowMoreButton(eventLink = event.eventlink!!)
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

    private fun setKnowMoreButton(eventLink: String) {
        binding.edKnowMoreButton.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(eventLink)
                )
            )
        }
    }

    private fun getImageId(eventType: String): Int {
        return when(eventType){
            "Android" -> R.drawable.androidlogo
            "Flutter" -> R.drawable.flutterlogo
            "Cloud" -> R.drawable.cloudlogo
            "Tensorflow" -> R.drawable.tensorflowlogo
            "Firebase" -> R.drawable.firebaselogo
            else -> -1
        }
    }
}