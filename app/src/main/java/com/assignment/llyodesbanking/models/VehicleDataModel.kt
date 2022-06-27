package com.assignment.llyodesbanking.models

data class VehicleDataModel(val count: Int, val nxt: String, val previous: String,
                            val results: List<VehicleItem>) {
}