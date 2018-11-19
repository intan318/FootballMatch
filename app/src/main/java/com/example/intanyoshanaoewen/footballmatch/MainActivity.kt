package com.example.intanyoshanaoewen.footballmatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.naufa.matchSchedule.Adapter.FragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Intan Yoshana Oewen on 13/11/2018.
 */

class MainActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment()
    }

    private fun setFragment(){
        pagerAdapter = FragmentAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

}
