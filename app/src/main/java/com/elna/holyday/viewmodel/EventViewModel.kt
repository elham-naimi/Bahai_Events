package com.elna.holyday.viewmodel

import android.arch.lifecycle.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.elna.holyday.model.Years
import android.util.Log
import com.elna.holyday.FirebaseRepository
import com.elna.holyday.model.Event
import com.elna.holyday.presenter.Presenter
import com.google.firebase.database.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EventViewModel : ViewModel() {

    var holyDays : MutableLiveData<List<Event>> = MutableLiveData()

    companion object {
        private val TAG : String = EventViewModel::class.java.simpleName
    }

    fun getArticles(): LiveData<List<Event>> {
        if (holyDays.value == null) {
            Log.i("TAG","EventViewModel :: Subscribing")
            FirebaseRepository().createIfNotExists()
                    .subscribeOn(Schedulers.computation())
                    .map{ years->Presenter.getEvents(years) }
                    .map { result -> Presenter.sortEvents(result) }
                    .observeOn(Schedulers.computation())
                    .subscribe { res -> holyDays.postValue(res) }


           // Presenter.getUpcomingEvents().subscribe() { result -> holyDays.postValue(result)}
        }
        return holyDays
    }
}