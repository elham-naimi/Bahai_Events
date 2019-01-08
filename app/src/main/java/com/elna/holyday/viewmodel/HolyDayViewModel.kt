package com.elna.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.elna.datetime.DateTime
import com.elna.db.HolyDayRepository
import com.elna.db.HolyDaysDatabase
import com.elna.db.HolyDaysForGivenYear
import com.elna.model.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import com.elna.presenter.Presenter
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import org.threeten.bp.LocalDateTime
import kotlin.collections.ArrayList

import org.threeten.bp.temporal.ChronoUnit.DAYS
import org.threeten.bp.temporal.ChronoUnit.MINUTES

public class HolyDayViewModel(private val app: Application) : AndroidViewModel(app) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)


    fun loadHolyDays(): Flowable<ArrayList<HolyDay>> {
        return Presenter.queryUpcomingHolydays(app,scope)
                .map { list-> list }

    }


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }



}
