package com.assignment.llyodesbanking.Api

import com.assignment.llyodesbanking.models.VehicleDataModel
import com.assignment.llyodesbanking.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getVehicles():Response<VehicleDataModel>
}