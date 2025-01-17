package com.pulsedonor.app.models.datas

import com.squareup.moshi.Json

class DatasResponse(

    @Json(name = "data")
    var data: List<Data>? = null,
)

class Data(

    @Json(name = "value")
    var value: String? = null,

    @Json(name = "text")
    var text: String? = null
)