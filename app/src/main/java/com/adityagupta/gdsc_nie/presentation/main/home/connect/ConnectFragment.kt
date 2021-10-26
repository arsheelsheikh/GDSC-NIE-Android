package com.adityagupta.gdsc_nie.presentation.main.home.connect

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.adityagupta.gdsc_nie.R
import com.adityagupta.gdsc_nie.databinding.FragmentConnectBinding
import com.adityagupta.gdsc_nie.databinding.TeamMemberItemBinding
import com.adityagupta.gdsc_nie.domain.adapters.TeamMemberAdapter
import android.view.MotionEvent
import com.adityagupta.gdsc_nie.data.local.TeamMemberInfo


class ConnectFragment : Fragment() {

    lateinit var binding: FragmentConnectBinding
    var recyclerAdapter = TeamMemberAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_connect,
            container,
            false
        )



        binding.cfRecyclerView.adapter = recyclerAdapter

        var members = listOf<TeamMemberInfo>(
            TeamMemberInfo("Aditya Gupta",R.drawable.aditya_gupta, "Technical Lead", "https://www.github.com/aadityagupta", "https://www.instagram.com/adi.tya_._"),
            TeamMemberInfo("Iresh Sharma", R.drawable.iresh_sharma, "GDSC NIE Lead", "https://www.github.com/aadityagupta", "https://www.instagram.com/adi.tya_._"),
            TeamMemberInfo("Deepthi Nanjunda", R.drawable.deepthi_n, "Editorial Lead", "https://www.github.com/aadityagupta", "https://www.instagram.com/adi.tya_._"),
            TeamMemberInfo("Raghav Kaushal", R.drawable.raghav_k,"CP Lead", "https://www.github.com/aadityagupta", "https://www.instagram.com/adi.tya_._"),
            TeamMemberInfo("Pranav B",R.drawable.pranav_b, "Technical Advisor", "https://www.github.com/aadityagupta", "https://www.instagram.com/adi.tya_._"),
            TeamMemberInfo("Apala S",R.drawable.apala_s, "Marketing Lead", "https://www.github.com/aadityagupta", "https://www.instagram.com/adi.tya_._"),
            TeamMemberInfo("Yash Chauhan", R.drawable.yash_chauhan,"CP Lead", "https://www.github.com/aadityagupta", "https://www.instagram.com/adi.tya_._")

        )
        members = members.sortedBy {
            it.name
        }

        recyclerAdapter.members = members

        return binding.root
    }


}