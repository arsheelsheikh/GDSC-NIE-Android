package com.adityagupta.gdsc_nie.presentation.main.home.connect

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.adityagupta.gdsc_nie.R
import com.adityagupta.gdsc_nie.databinding.FragmentConnectBinding

class ConnectFragment : Fragment() {

    lateinit var binding: FragmentConnectBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_connect,
            container,
            false
        )

        setUpDiscordButton()
        setUpSocialMediaButtons()


        return binding.root
    }

    private fun setUpDiscordButton() {
        binding.cfJoinDiscordButton.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://discord.gg/Yz48AJTpHM")
                )
            )
        }
    }

    private fun setUpSocialMediaButtons(){
        //Discord
        binding.cfDiscordButton.setOnClickListener {
            startActivity(
                Intent(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://discord.gg/Yz48AJTpHM")
                    )
                )
            )
        }

        //Instagram
        binding.cfInstagramButton.setOnClickListener {
            startActivity(
                Intent(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/gdsc_nie/")
                    )
                )
            )
        }

        //Twitter
        binding.cfTwitterbutton.setOnClickListener {
            startActivity(
                Intent(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/DscNie")
                    )
                )
            )
        }

        //Linkedin
        binding.cfLinkedinButton.setOnClickListener {
            startActivity(
                Intent(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/company/dsc-nie/about/")
                    )
                )
            )
        }
    }


}