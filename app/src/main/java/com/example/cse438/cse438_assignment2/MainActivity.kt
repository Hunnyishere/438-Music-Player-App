package com.example.cse438.cse438_assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cse438.cse438_assignment2.Fragment.HomeFragment

import com.example.cse438.cse438_assignment2.Fragment.PlaylistFragment
import com.example.cse438.cse438_assignment2.Fragment.RadioListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    public lateinit var fragmentAdapter: MyPagerAdapter
    public var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter=fragmentAdapter
        tab_main.setupWithViewPager(viewPager)

    }

    override fun onStart() {
        super.onStart()

//        var bundle: Bundle? = intent.extras
//        if (bundle != null) {
//            position = bundle!!.getInt("position")
//        }
//        //change page default value
//        viewPager.setCurrentItem(position);
    }


    class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount() : Int {
            return 3
        }

        override fun getItem(position: Int) : Fragment {
            return when (position) {
                0 -> {
                  HomeFragment()
                }
                1 -> {
                    PlaylistFragment()
                }
                else -> RadioListFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "home"
                1 -> "playlists"
                else ->"radio"
            }
        }

    }
}
