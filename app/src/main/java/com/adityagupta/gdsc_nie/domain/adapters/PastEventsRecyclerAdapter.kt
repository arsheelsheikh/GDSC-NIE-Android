package com.adityagupta.gdsc_nie.domain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adityagupta.gdsc_nie.R
import com.adityagupta.gdsc_nie.data.remote.PastEventsData.RecyclerData
import com.adityagupta.gdsc_nie.databinding.PastEventsRecyclerItemBinding
import com.adityagupta.gdsc_nie.presentation.main.home.HomeFragmentDirections
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import androidx.core.content.ContextCompat
import com.adityagupta.gdsc_nie.presentation.main.home.events.details.EventDetailsActivity


class PastEventsRecyclerAdapter: RecyclerView.Adapter<PastEventsRecyclerAdapter.MyViewHolder>()  {

    var pastEventsList = emptyList<RecyclerData>()


    inner class MyViewHolder(val binding: PastEventsRecyclerItemBinding): RecyclerView.ViewHolder(binding.root){
    }

    private val diffCallback = object : DiffUtil.ItemCallback<RecyclerData>(){
        override fun areItemsTheSame(oldItem: RecyclerData, newItem: RecyclerData): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: RecyclerData, newItem: RecyclerData): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var events: List<RecyclerData>
        get() = differ.currentList
        set(value) {differ.submitList(value) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            PastEventsRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            val event = events[position]
            peEventTitleTextView.text = event.title
            peItemSpeakerTextView.text = event.speaker
            peItemEventImageView.setImageResource(getImageId(event.type!!))
            /*peItemCardView.setOnClickListener{ it ->
                peItemCardView
                    .findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToEventsDetailFragment(event))
            }*/
            peItemCardView.setOnClickListener {
                val i = Intent(peItemCardView.context, EventDetailsActivity::class.java)
                i.putExtra("event", event)
                peItemCardView.context.startActivity(i)
            }
        }

    }

    override fun getItemCount() = events.size

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