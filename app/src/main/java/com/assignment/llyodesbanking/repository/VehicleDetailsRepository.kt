package com.assignment.llyodesbanking.repository

import com.assignment.llyodesbanking.Api.ApiService
import javax.inject.Inject

class VehicleDetailsRepository @Inject constructor(private val apiService: ApiService){

    suspend fun getVehicleDetails() = apiService.getVehicles()
}