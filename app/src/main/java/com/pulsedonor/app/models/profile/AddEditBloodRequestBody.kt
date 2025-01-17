package com.pulsedonor.app.models.profile

import com.squareup.moshi.Json

data class AddEditBloodRequestBody(
    @Json(name = "bloodTypeId")
    var bloodTypeId: Int? = null,

    @Json(name = "quantity")
    var quantity: Double? = null,

    @Json(name = "urgenceTypeId")
    var urgenceTypeId: Int? = null,

    @Json(name = "hospitalId")
    var hospitalId: Int? = null,

    @Json(name = "donationDate")
    var donationDate: String? = null,

    @Json(name = "donationTime")
    var donationTime: String? = null,

    @Json(name = "firstName")
    var firstName: String? = null,

    @Json(name = "lastName")
    var lastName: String? = null,

    @Json(name = "age")
    var age: Int? = null
)
