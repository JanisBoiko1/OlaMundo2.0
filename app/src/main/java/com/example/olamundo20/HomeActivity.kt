package com.example.olamundo20

import android.location.LocationListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Chronometer
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.olamundo20.databinding.ActivityHomeBinding
import com.example.olamundo20.domain.ChronometerViewModel
import com.example.olamundo20.domain.LiveDataTimerViewModel
import java.util.jar.Manifest

class HomeActivity : LogActivity() {
    lateinit var binding : ActivityHomeBinding

//    //acompanhar localização
//    private val mGpsListener: LocationListener = MyLocationListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        //live data
//        val mLiveDataTimerViewModel: LiveDataTimerViewModel = ViewModelProvider(this).get(
//            LiveDataTimerViewModel :: class.java)
//        subscribe(mLiveDataTimerViewModel)

//        //permissão de localização
//        val permissionLocation = PermissionUtils.request(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
//        if (permissionLocation){
//            this.bindLocationListener()
//        }

    }
    //live data
//    fun subscribe(mLiveDataTimerViewModel: LiveDataTimerViewModel) {
//        val elapsedTimeObserver = Observer<Log>{ newText ->
//            this getResources().getString( R.String.seconds, newText)
//            findViewById<TextView>(R.id.timerTextView).setText(newText)
//        }
//        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
//    }

}