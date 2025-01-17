package com.pulsedonor.app.models.datas

import com.squareup.moshi.Json

class BloodTypesResponse(

    @Json(name = "data")
    var data: List<BloodTypesData>? = null,
)

class BloodTypesData(

    @Json(name = "value")
    var value: String? = null,

    @Json(name = "text")
    var text: String? = null

)