package com.assignment.test.repository

import com.assignment.test.Api.ApiService
import javax.inject.Inject

class VehicleDetailsRepository @Inject constructor(private val apiService: ApiService){

    suspend fun getVehicleDetails() = apiService.getVehicles()
}