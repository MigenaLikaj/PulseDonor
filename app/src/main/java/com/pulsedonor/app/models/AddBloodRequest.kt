package com.pulsedonor.app.models

import com.squareup.moshi.Json

data class AddBloodRequest(
    @Json(name = "authorId")
    var authorId: String? = null,

    @Json(name = "bloodTypeId")
    var bloodTypeId: Int? = null,

    @Json(name = "hospitalId")
    var hospitalId: Int? = null,

    @Json(name = "urgencTypeId")
    var urgencTypeId: Int? = null,

    @Json(name = "donorId")
    var donorId: String? = null,

    @Json(name = "firstName")
    var firstName: String? = null,

    @Json(name = "lastName")
    var lastName: String? = null,

    @Json(name = "age")
    var age: Int? = null,

    @Json(name = "quantity")
    var quantity: Int? = null,

    @Json(name = "postKey")
    var postKey: String? = null,

    @Json(name = "donationDate")
    var donationDate: String? = null,

    @Json(name = "donationTime")
    var donationTime: String? = null
)
