package com.pulsedonor.app.models.hall_of_fame

import com.squareup.moshi.Json

class GetTopThreeDonorsResponse(

    @Json(name = "data")
    var data: List<TopThreeDonorsData>? = null,
)

class TopThreeDonorsData(

    @Json(name = "fullName")
    var fullName: String? = null,

    @Json(name = "count")
    var count: Int? = null,
)