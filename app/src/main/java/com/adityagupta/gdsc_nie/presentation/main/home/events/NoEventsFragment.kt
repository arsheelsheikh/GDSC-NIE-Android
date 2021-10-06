package com.adityagupta.gdsc_nie.presentation.main.home.events

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.adityagupta.gdsc_nie.R
import com.adityagupta.gdsc_nie.databinding.FragmentNoEventsBinding


class NoEventsFragment : Fragment() {

    lateinit var binding: FragmentNoEventsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_no_events, container, false)

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


}