package com.assignment.test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import com.assignment.test.R
import com.assignment.test.databinding.ActivityMainBinding
import com.assignment.test.viewmodel.VehicleDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: VehicleDetailsViewModel by viewModels()
    private lateinit var vehiclesAdapter: VehiclesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpRecycleView()
    }

    private fun setUpRecycleView(){
        val progressBar = binding.progressBar
        val recVehicles = binding.rvVehicles
        vehiclesAdapter = VehiclesAdapter()

        recVehicles.apply {
            adapter = vehiclesAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
        }

        viewModel.responseVeh.observe(this, { listTvShows ->
            //viewModel.progress.setValue(8) //View.VISIBLE
            progressBar.apply {
                visibility =  View.GONE
                viewModel.progress.value = 8}
            vehiclesAdapter.vehicleList = listTvShows
        })
    }
}