package com.elna.holyday.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elna.holyday.Constants
import com.elna.holyday.R
import com.elna.holyday.model.Event
import com.elna.holyday.ui.adapter.HolyDayListAdapter
import com.elna.holyday.viewmodel.EventViewModel

private lateinit var holyDayViewModel: EventViewModel

lateinit var onClickListener: View.OnClickListener

class EventFragment : Fragment() {
    private   var adapter: HolyDayListAdapter? = null
  companion object {

        val TAG : String = EventFragment::class.java.simpleName
        val EVENT : String = "EVENT_TYPE"

        fun newInstance(tab : Int): EventFragment {
            val args: Bundle = Bundle()
            args.putSerializable(EVENT, tab)
            val fragment = EventFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView : RecyclerView  = inflater.inflate(R.layout.item_list,container, false) as RecyclerView
        rootView.layoutManager = LinearLayoutManager(activity)
        adapter = HolyDayListAdapter(activity, View.OnClickListener { })
        rootView.adapter = adapter
        return rootView
    }


   override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       var type_selected : Int =  arguments?.get(EVENT) as Int
       var results = ArrayList<Event>()
       holyDayViewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)
       holyDayViewModel.getArticles().observe(this, Observer<List<Event>> { items ->
            if (items != null) {

                if(type_selected == Constants.ALL)
                    adapter?.setWords(items)
                else {
                    for (item in items) {
                          if (item.type == type_selected)
                            results.add(item)
                    }
                    adapter?.setWords(results)
                }
            }
        })
    }


}

