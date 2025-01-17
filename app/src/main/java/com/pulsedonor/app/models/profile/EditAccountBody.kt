package com.pulsedonor.app.models.profile

import com.squareup.moshi.Json

class EditAccountBody(

    @Json(name = "firstName")
    var firstName: String? = null,

    @Json(name = "lastName")
    var lastName: String? = null,

    @Json(name = "email")
    var email: String? = null,

    @Json(name = "bloodTypeId")
    var bloodTypeId: Int? = null,

    @Json(name = "primaryCityId")
    var primaryCityId: Int? = null,

    @Json(name = "primaryCityIdToDelete")
    var primaryCityIdToDelete: Int? = null,

    @Json(name = "secondaryCities")
    var secondaryCities: List<Int>? = null,

    @Json(name = "secondaryCityIdsToDelete")
    var secondaryCityIdsToDelete: List<Int>? = null,

    @Json(name = "status")
    var status: Boolean? = null

)