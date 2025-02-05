package com.adityagupta.gdsc_nie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.adityagupta.gdsc_nie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.adityagupta.gdsc_nie.presentation.main.home.HomeFragment
import com.adityagupta.gdsc_nie.presentation.main.home.about.AboutFragment
import com.adityagupta.gdsc_nie.presentation.main.home.blogs.BlogsFragment
import com.adityagupta.gdsc_nie.presentation.main.home.connect.ConnectFragment


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val homeFragment: Fragment = HomeFragment()
    private val aboutFragment: Fragment = AboutFragment()
    private val connectFragment: Fragment = ConnectFragment()
    private val blogsFragment: Fragment = BlogsFragment()

    private val fm: FragmentManager = supportFragmentManager
    var active: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        fm.beginTransaction().add(R.id.homeActivityFragmentContainerView, aboutFragment, "2").hide(aboutFragment).commit();
        fm.beginTransaction().add(R.id.homeActivityFragmentContainerView, connectFragment, "3").hide(connectFragment).commit();
        fm.beginTransaction().add(R.id.homeActivityFragmentContainerView, blogsFragment, "4").hide(blogsFragment).commit();
        fm.beginTransaction().add(R.id.homeActivityFragmentContainerView, homeFragment, "1").commit();

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.homeBottomNavigation -> {
                    fm.beginTransaction().hide(active).show(homeFragment).commit();
                    active = homeFragment;
                    true

                }
                R.id.aboutBottomNavigation -> {
                    fm.beginTransaction().hide(active).show(aboutFragment).commit();
                    active = aboutFragment;
                    true
                }
                R.id.connectBottomNavigation -> {
                    fm.beginTransaction().hide(active).show(connectFragment).commit();
                    active = connectFragment;
                    true
                }
                R.id.blogBottomNavigation -> {
                    fm.beginTransaction().hide(active).show(blogsFragment).commit();
                    active = blogsFragment;
                    true
                }
                else -> false
            }
        }
    }


}