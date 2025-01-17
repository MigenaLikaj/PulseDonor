package com.pulsedonor.app.models.profile

import com.squareup.moshi.Json

class JoinGroupBody(

    @Json(name = "joinCode")
    var joinCode: String? = null,
    
    )