package com.adityagupta.gdsc_nie.domain.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adityagupta.gdsc_nie.data.remote.EventDetailData.SpeakerData
import com.adityagupta.gdsc_nie.databinding.EventsSpeakersBinding
import com.bumptech.glide.Glide

class SpeakersAdapter(val context: Context): RecyclerView.Adapter<SpeakersAdapter.MyViewHolder>()  {

    var speakersList = emptyList<SpeakerData>()


    inner class MyViewHolder(val binding: EventsSpeakersBinding): RecyclerView.ViewHolder(binding.root){

    }

    private val diffCallback = object : DiffUtil.ItemCallback<SpeakerData>(){
        override fun areItemsTheSame(oldItem: SpeakerData, newItem: SpeakerData): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: SpeakerData, newItem: SpeakerData): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var speakers: List<SpeakerData>
        get() = differ.currentList
        set(value) {differ.submitList(value) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            EventsSpeakersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            val speaker = speakers[position]
            speakerTitle.text = speaker.title
            Log.i("firebase", speaker.toString())
            Glide.with(context)
                .load(speaker.link)
                .into(speakerImageView)
        }

    }

    override fun getItemCount() = speakers.size

}