package com.elna.holyday.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elna.holyday.R
import com.elna.holyday.model.*
import kotlinx.android.synthetic.main.list_item.view.*
import org.threeten.bp.format.DateTimeFormatter

public class HolyDayListAdapter(val context: Context?, val onClickListener: View.OnClickListener) : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        private val TAG : String = HolyDayListAdapter.javaClass.simpleName
    }

    private var items : List<Event>? = null
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
                Log.i("Adapter", "onCreateViewHolder ")
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("Adapter", "onBindViewHolder ")
                if(items != null) {
            holder?.eventName?.text = items?.get(position)?.name
            val formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy")
            holder.eventDate.text = items?.get(position)?.date?.format(formatter)
            holder.daysLeft.text =  items?.get(position)?.daysleft.toString()
        }

    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        if(items != null)
        if(items?.isNotEmpty()!!)
            return items?.size!!
        return 0
    }

     internal fun setWords(holyDayList : List<Event>) {
        items = holyDayList;
        notifyDataSetChanged()
    }
}


class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val eventName = view.textViewEventName
    val eventDate = view.textViewEventDate
    val daysLeft = view.textViewEventDaysLeft

}
