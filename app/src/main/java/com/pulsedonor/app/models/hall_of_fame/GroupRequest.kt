package com.pulsedonor.app.models.hall_of_fame

import com.squareup.moshi.Json

data class GroupRequest(
    @Json(name = "name")
    var name: String? = null,

    @Json(name = "description")
    var description: String? = null,

    @Json(name = "cityId")
    var cityId: Int? = null
)
