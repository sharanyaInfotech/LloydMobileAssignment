package com.assignment.llyodesbanking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.llyodesbanking.databinding.ActivityMainBinding
import com.assignment.llyodesbanking.ui.VehiclesAdapter
import com.assignment.llyodesbanking.viewmodel.VehicleDetailsViewModel
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