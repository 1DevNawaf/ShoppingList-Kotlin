package com.example.shoppinglist

import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingFromCoordinates {
    @GET("maps/api/geocode/json")
    suspend fun getAddressFromCoordinates(
        @Query("latlng") latLng: String,
        @Query("ket") apiKey : String
    ):GeocodingResponse

}