package com.example.hilt.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hilt.R
import com.example.hilt.data.model.Main
import com.mindorks.framework.mvvm.ui.main.adapter.MainAdapter
import com.example.hilt.ui.main.viewmodel.MainViewModel
import com.mindorks.framework.mvvm.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationManager
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.hilt.databinding.ActivityMainBinding
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hilt.data.db.entity.MainEntity

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint


class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var adapter: MainAdapter

    lateinit var binding: ActivityMainBinding

    var permssionGranted: Boolean = false

    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission granted, start getting the location
            permssionGranted = true
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
        setSupportActionBar(binding.toolbar)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        requestPermission()
        setupUI()
        setupObserver()

    }


    @SuppressLint("MissingPermission")
    private fun getLocation() {
        // Check if location services are enabled
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showToast("Please enable GPS")
            return
        }

        // Request the last known location
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    // Location retrieved successfully, use the location data
                    val latitude = location.latitude
                    val longitude = location.longitude
                    mainViewModel.getWeather(latitude, longitude, 1)
                } else {
                    // Location is null, handle the case where the location is not available
                    showToast("Location not available")
                }
            }
            .addOnFailureListener { exception ->
                // Handle the case where an error occurred while fetching the location
                showToast("Error getting location: ${exception.message}")
            }
// uncomment this for accurate result but it will take long time
//        fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
//            override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token
//
//            override fun isCancellationRequested() = false
//        })
//            .addOnSuccessListener { location: Location? ->
//                if (location != null) {
//                    // Location retrieved successfully, use the location data
//                    val latitude = location.latitude
//                    val longitude = location.longitude
//                    mainViewModel.getWeather(latitude, longitude, 1)
//                } else {
//                    // Location is null, handle the case where the location is not available
//                    showToast("Location not available")
//                }
//
//            }
//            .addOnFailureListener {  exception->
//            // Handle the case where an error occurred while fetching the location
//            showToast("Error getting location: ${exception.message}") }
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.INTERNET),
            101
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_NETWORK_STATE),
            101
        )

        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

    }


    private fun setupUI() {

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val mainList: MutableList<Main> = mutableListOf()
        adapter = MainAdapter(mainList)
        binding.recyclerView.adapter = adapter
        binding.progressBar.visibility = View.GONE

    }


    private fun setupObserver() {
        mainViewModel.response.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { finalResponse ->
                        finalResponse.main!!.requestDAte = it.message!!
                        renderList(finalResponse.main!!, it.message!!)
                        showToast("Inserted Successfully")
                    }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    mainViewModel.getRecorde().observe(this, Observer {
                        renderList(it)
                    })
                }
            }
        })

    }


    private fun renderList(main: Main, date: String) {
        main.requestDAte = date
        adapter.addData(main)

    }

    private fun renderList(main: List<MainEntity>) {
        adapter.addDataFromDb(main)


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> {
                if (permssionGranted) {
                    getLocation()
                } else
                {
                    showToast("Check Permission please")
                    locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}


