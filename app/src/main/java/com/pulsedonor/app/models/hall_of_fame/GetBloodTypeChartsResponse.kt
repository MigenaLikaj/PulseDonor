package com.pulsedonor.app.models.hall_of_fame

import com.squareup.moshi.Json

class GetBloodTypeChartsResponse(

    @Json(name = "data")
    var data: List<BloodTypeChartsData>? = null,
)

class BloodTypeChartsData(

    @Json(name = "bloodType")
    var bloodType: String? = null,

    @Json(name = "count")
    var count: Int? = null,

    @Json(name = "percentage")
    var percentage: Double? = null
)