package com.elna.holyday.viewmodel

import android.arch.lifecycle.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.elna.holyday.model.Years
import android.util.Log
import com.elna.holyday.model.Event
import com.elna.holyday.presenter.Presenter
import com.google.firebase.database.*

class EventViewModel : ViewModel() {

    var holyDays : MutableLiveData<List<Event>> = MutableLiveData()
    lateinit var firebaseDatabase : FirebaseDatabase

    companion object {
        private val TAG : String = EventViewModel::class.java.simpleName
    }

    fun getArticles(): LiveData<List<Event>> {
        if (holyDays.value == null) {
            Log.i(TAG, "FirebaseDatabase.getInstance()")
            firebaseDatabase = FirebaseDatabase.getInstance();
                    firebaseDatabase.getReference()
                    .addValueEventListener(object  : ValueEventListener{
                        override fun onCancelled(p0: DatabaseError) {
                            Log.i(TAG, "onError")
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {
                            Log.i(TAG, "onDataChange")
                            if (snapshot.exists()) {

                                var filteredYears = Years()
                                val results =  snapshot.getValue(Years::class.java)
                                results?.let { filteredYears = Presenter.filter(it)}
                               /* Presenter.getUpcomingEvents(filteredYears)
                                        .observeOn(Schedulers.computation())
                                        .subscribe { events ->
                                            Log.i(TAG,"posting events"+events.feast.size)
                                            holyDays.postValue(events)*/
                              holyDays.postValue(Presenter.getUpcomingEvents(filteredYears))


                            }
                        }

                    })
        }
        return holyDays
    }

    private inner class Deserializer : Function<DataSnapshot> {
        fun apply(dataSnapshot: DataSnapshot): Years? {
            return dataSnapshot.getValue<Years>(Years::class.java!!)
        }
    }
  //  private val holyDaysLiveData = Transformations.map(liveData){ liveData-> liveData.getValue(Years::class.java!!)}



}