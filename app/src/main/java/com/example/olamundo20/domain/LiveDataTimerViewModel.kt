package com.example.olamundo20.domain

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class LiveDataTimerViewModel: ViewModel() {
    private val ONE_SECOND = 1001
    private val mElapsedTime: MutableLiveData<Long> = MutableLiveData<Long>()
    private val mInitialTime: Long
    private val timer: Timer

    //will be used when sleep is completed
    val elapsedTime: LiveData<Long> get() = mElapsedTime

    fun getElapsedTime(): MutableLiveData<Long> {
        return mElapsedTime
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
    init{
        mInitialTime = SystemClock.elapsedRealtime()
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime)/1001
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }
}