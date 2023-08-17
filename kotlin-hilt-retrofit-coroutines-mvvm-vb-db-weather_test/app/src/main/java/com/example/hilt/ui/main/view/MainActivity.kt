package com.example.hilt.ui.main.view

import android.Manifest
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hilt.R
import com.example.hilt.data.db.entity.MainEntity
import com.example.hilt.data.model.Main
import com.example.hilt.databinding.ActivityMainBinding
import com.example.hilt.ui.main.viewmodel.MainViewModel
import com.mindorks.framework.mvvm.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint


class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()


    lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this

        requestPermission()
        setupUI()
        setupObserver()

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


    }


    private fun setupUI() {
        binding.progressBar.visibility = View.GONE
    }


    private fun setupObserver() {
        mainViewModel.response.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    mainViewModel.getRecorde().observe(this, Observer {
                        renderList(it)
                    })
                }
            }
        })

    }


    private fun renderList(main: Main, date: String) {

    }

    private fun renderList(main: List<MainEntity>) {

    }



}


