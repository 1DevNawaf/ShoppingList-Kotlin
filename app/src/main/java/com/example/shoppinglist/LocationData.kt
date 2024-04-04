package com.example.shoppinglist

data class LocationData(
    val latitude:Double,
    val longitude:Double
)

data class GeocodingResponse(
    val result:List<GeoCodingResult>,
    val status:String
)

data class GeoCodingResult(
    val formatted_address:String
)
