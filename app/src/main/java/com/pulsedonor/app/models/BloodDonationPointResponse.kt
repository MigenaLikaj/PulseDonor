package com.pulsedonor.app.models

import com.squareup.moshi.Json

class BloodDonationPointResponse(

    @Json(name = "data")
    var data: List<Bloodpoints>? = null,
)

class Bloodpoints(

    @Json(name = "id")
    var id: Int? = null,

    @Json(name = "addesss")
    var addesss: String? = null,

    @Json(name = "startTime")
    var startTime: String? = null,

    @Json(name = "endTime")
    var endTime: String? = null,

    @Json(name = "longitude")
    var longitude: String? = null,

    @Json(name = "latitude")
    var latitude: String? = null,

    )