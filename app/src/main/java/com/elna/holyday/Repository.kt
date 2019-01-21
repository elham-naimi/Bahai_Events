package com.elna.holyday

import android.util.Log
import com.elna.holyday.model.Year
import com.elna.holyday.model.Years
import com.elna.holyday.presenter.Presenter
import com.elna.holyday.viewmodel.EventViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import com.google.firebase.database.DatabaseReference
import com.google.firebase.FirebaseException

class  FirebaseRepository {

    protected  var database: DatabaseReference = FirebaseDatabase.getInstance().reference


    fun test():Observable<Years>{
        return Observable.fromCallable { createObservable() }
    }

    private fun createObservable(): Years? {
        var results = Years()
        Log.i("TAG", "ObservableOnSubscribe")
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.i("TAG", "Error")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.i("TAG", "Observable.create")
                if (dataSnapshot.exists()) {
                    Log.i("TAG", "dataSnapshot.exists()"+dataSnapshot.exists())
                    results = dataSnapshot.getValue(Years::class.java)!!
                }
            }
        })
        return results
    }


    fun createIfNotExists(): Observable<Years> {
        return Observable.create {
            emitter ->
                Log.i("TAG","ObservableOnSubscribe")
                database.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        Log.i("TAG","Observable.create")
                        if (dataSnapshot.exists()) {
                            val results =  dataSnapshot.getValue(Years::class.java)
                            Log.i("TAG","dataSnapshot.exists()"+results?.years?.size)
                            results?.let { result -> emitter.onNext(result)
                                emitter.onComplete() }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        emitter.onError(FirebaseException(databaseError.message))
                    }
                })
            }
        }
}



