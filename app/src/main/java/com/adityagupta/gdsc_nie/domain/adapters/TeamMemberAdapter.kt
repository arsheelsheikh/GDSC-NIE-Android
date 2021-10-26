package com.adityagupta.gdsc_nie.domain.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adityagupta.gdsc_nie.data.local.TeamMemberInfo
import com.adityagupta.gdsc_nie.data.remote.PastEventsData.RecyclerData
import com.adityagupta.gdsc_nie.databinding.TeamMemberItemBinding


class TeamMemberAdapter: RecyclerView.Adapter<TeamMemberAdapter.MyViewHolder>()  {

    var teamMembersList = emptyList<String>()


    inner class MyViewHolder(val binding: TeamMemberItemBinding): RecyclerView.ViewHolder(binding.root){
    }

    private val diffCallback = object : DiffUtil.ItemCallback<TeamMemberInfo>(){
        override fun areItemsTheSame(oldItem: TeamMemberInfo, newItem: TeamMemberInfo): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: TeamMemberInfo, newItem: TeamMemberInfo): Boolean {
            return oldItem.name == newItem.name
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var members: List<TeamMemberInfo>
        get() = differ.currentList
        set(value) {differ.submitList(value) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            TeamMemberItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            var member = members[position]
            tmiName.text = member.name
            tmiPosition.text = member.position
            shapeableImageView.setImageResource(member.imageId)
        }

    }

    override fun getItemCount() = members.size

}