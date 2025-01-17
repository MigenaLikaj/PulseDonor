package com.pulsedonor.app.models.datas

import com.squareup.moshi.Json

class CitiesResponse(

    @Json(name = "data")
    var data: List<CitiesData>? = null,
)

class CitiesData(

    @Json(name = "value")
    var value: String? = null,

    @Json(name = "text")
    var text: String? = null

)