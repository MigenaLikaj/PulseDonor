package com.pulsedonor.app.models.profile

import com.squareup.moshi.Json

class AddGroupBody(
    @Json(name = "name")
    var name: String? = null,

    @Json(name = "description")
    var description: String? = null,

    @Json(name = "cityId")
    var cityId: Int? = null
)
