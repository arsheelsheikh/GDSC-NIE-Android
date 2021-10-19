package com.adityagupta.gdsc_nie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.adityagupta.gdsc_nie.databinding.ActivityMainBinding
import com.adityagupta.gdsc_nie.domain.adapters.HomePagerAdapter
import com.adityagupta.gdsc_nie.presentation.main.home.events.NoEventsFragment
import com.adityagupta.gdsc_nie.presentation.main.home.events.pastEvents.PastEventsFragment
import dagger.hilt.android.AndroidEntryPoint
import com.adityagupta.gdsc_nie.presentation.main.home.HomeFragment
import com.adityagupta.gdsc_nie.presentation.main.home.connect.ConnectFragment


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val homeFragment: Fragment = HomeFragment()
    val connectFragment: Fragment = ConnectFragment()
    val fm: FragmentManager = supportFragmentManager
    var active: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        fm.beginTransaction().add(R.id.homeActivityFragmentContainerView, connectFragment, "2").hide(connectFragment).commit();
        fm.beginTransaction().add(R.id.homeActivityFragmentContainerView, homeFragment, "1").commit();

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.homeBottomNavigation -> {
                    fm.beginTransaction().hide(active).show(homeFragment).commit();
                    active = homeFragment;
                    true

                }
                R.id.connectBottomNavigation -> {
                    fm.beginTransaction().hide(active).show(connectFragment).commit();
                    active = connectFragment;
                    true
                }
                else -> false
            }
        }
    }


}