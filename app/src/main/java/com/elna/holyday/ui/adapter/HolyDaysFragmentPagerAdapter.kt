package com.elna.holyday.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.elna.holyday.ui.EventFragment
import com.elna.holyday.ui.ItemListActivity

class HolyDaysFragmentPagerAdapter(itemListActivity: ItemListActivity, supportFragmentManager: FragmentManager?) : FragmentPagerAdapter(supportFragmentManager) {

    override fun getItem(position: Int): Fragment {
    var fragment = Fragment()
    fragment = EventFragment.newInstance(position)
     return fragment
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var result = String()
        when(position){
            0 -> result = "All"
            1 -> result = "Feast"
            2 -> result = "Holy Day"

        }
        return result
    }

}
