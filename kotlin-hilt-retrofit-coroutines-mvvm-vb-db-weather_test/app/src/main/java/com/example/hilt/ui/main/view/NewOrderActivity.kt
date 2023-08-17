package com.example.hilt.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hilt.R
import com.example.hilt.data.model.Item
import com.example.hilt.databinding.ActivityNewOrderBinding
import com.example.hilt.ui.main.adapter.GridAdapter
import com.example.hilt.ui.main.interfaces.OnItemClick
import com.example.hilt.ui.main.viewmodel.NewOrderViewModel

class NewOrderActivity : AppCompatActivity() ,OnItemClick{

    private lateinit var adapter: GridAdapter
    lateinit var binding:ActivityNewOrderBinding
    private val newOrderViewModel: NewOrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_order)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = newOrderViewModel
        binding.lifecycleOwner = this
        setupUI()
    }
    private fun setupUI() {
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        adapter = GridAdapter(newOrderViewModel.getListOfItem(),this)
        binding.recyclerView.adapter = adapter

    }

    override fun onClick(item: Item) {

    }
}