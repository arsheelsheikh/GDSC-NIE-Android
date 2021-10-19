package com.adityagupta.gdsc_nie.presentation.main.home.connect

import android.os.Bundle
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
            TeamMemberInfo("Aditya Gupta", "Technical Lead", "https://www.github.com/aadityagupta", "https://www.instagram.com/adi.tya_._"),
            TeamMemberInfo("Iresh Sharma", "GDSC NIE Lead", "https://www.github.com/aadityagupta", "https://www.instagram.com/adi.tya_._"),
            TeamMemberInfo("Dharithri Dhatoo", "Management Lead", "https://www.github.com/aadityagupta", "https://www.instagram.com/adi.tya_._"),
            TeamMemberInfo("Raghav Kaushal", "CP Lead", "https://www.github.com/aadityagupta", "https://www.instagram.com/adi.tya_._"),
            TeamMemberInfo("Pranav B", "Technical Advisor", "https://www.github.com/aadityagupta", "https://www.instagram.com/adi.tya_._"),
            TeamMemberInfo("Apala S", "Marketing Lead", "https://www.github.com/aadityagupta", "https://www.instagram.com/adi.tya_._"),
            TeamMemberInfo("Yash Chauhan", "CP Lead", "https://www.github.com/aadityagupta", "https://www.instagram.com/adi.tya_._")

        )

        recyclerAdapter.members = members

        return binding.root
    }


}