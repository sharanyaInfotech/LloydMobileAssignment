package com.assignment.test.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.test.Api.ApiService
import com.assignment.test.models.VehicleItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

//class VehicleDetailsViewModel @ViewModelInject constructor(private val repository: VehicleDetailsRepository) : ViewModel() {
class VehicleDetailsViewModel @ViewModelInject constructor(private val apiHelper: ApiService) : ViewModel() {
    var progress = MutableLiveData<Int>()
    //Coroutine exceptionhandler
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _response.postValue(emptyList())
    }

    private val _response = MutableLiveData<List<VehicleItem>>()
    val responseVeh: LiveData<List<VehicleItem>>
    get() = _response

    init {
        progress.setValue(0) //View.VISIBLE
        getAllVehicleList()
    }



    private fun getAllVehicleList() = viewModelScope.launch(exceptionHandler) {
        apiHelper.getVehicles().let { response ->
            if(response.isSuccessful){
                _response.postValue(apiHelper.getVehicles().body()?.results)
            }else{
                Log.d("tag", "getAllVehicleDetails Error: ${apiHelper.getVehicles().code()}")
            }

        }
    }
}