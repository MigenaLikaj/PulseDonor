package com.pulsedonor.app.models.auth

import com.squareup.moshi.Json

class GeneralResponse(
    @Json(name = "data")
    var data: String? = null

)

class GeneralResponseInt(
    @Json(name = "data")
    var data: Int? = null

)