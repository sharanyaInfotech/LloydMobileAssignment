package com.assignment.test.Api

import com.assignment.test.models.VehicleDataModel
import com.assignment.test.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getVehicles():Response<VehicleDataModel>
}