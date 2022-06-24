package com.assignment.test.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.test.models.VehicleItem
import com.assignment.test.repository.VehicleDetailsRepository
import kotlinx.coroutines.launch

class VehicleDetailsViewModel @ViewModelInject constructor(private val repository: VehicleDetailsRepository) :
    ViewModel() {

    private val _response = MutableLiveData<List<VehicleItem>>()
    val responseVeh: LiveData<List<VehicleItem>>
    get() = _response

    init {
        getAllTvShows()
    }

    private fun getAllTvShows() = viewModelScope.launch {
        //repository.getVehicleDetails().let {response ->

            if (repository.getVehicleDetails().isSuccessful){
                _response.postValue(repository.getVehicleDetails().body()?.results)
                Log.d("tag", "vEHICLE LIST: ${_response}")
            }else{
                Log.d("tag", "getAllVehicleDetails Error: ${repository.getVehicleDetails().code()}")
            }
//        }
    }
}