package com.pulsedonor.app.models.auth

import com.squareup.moshi.Json

class SignInBody(

    @Json(name = "email")
    var email: String? = null,

    @Json(name = "password")
    var password: String? = null

)