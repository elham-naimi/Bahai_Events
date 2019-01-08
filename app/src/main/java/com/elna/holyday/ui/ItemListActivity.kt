package com.elna.holyday.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.elna.holyday.R
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.elna.holyday.ui.adapter.HolyDaysFragmentPagerAdapter

class ItemListActivity : AppCompatActivity() {

    private var twoPane: Boolean = false
    private var TAG = ItemListActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        setSupportActionBar(toolbar)
        toolbar.title = title

        if (item_detail_container != null) {
             twoPane = true
        }

        val viewPager = findViewById(R.id.viewPagerHolyDays) as ViewPager

        // Create an adapter that knows which fragment should be shown on each page
        val adapter = HolyDaysFragmentPagerAdapter(this, supportFragmentManager)

        // Set the adapter onto the view pager
         viewPager.adapter = adapter

        val tabLayout = findViewById(R.id.tabLayoutHolyDays) as TabLayout
        tabLayout.setupWithViewPager(viewPager)

    }
}

