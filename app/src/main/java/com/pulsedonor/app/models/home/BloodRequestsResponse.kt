package com.pulsedonor.app.models.home

import com.squareup.moshi.Json

class BloodRequestsResponse(

    @Json(name = "data")
    var data: List<BloodRequestsData>? = null,
)

class BloodRequestsData(

    @Json(name = "id")
    var id: Int? = null,

    @Json(name = "authorId")
    var authorId: String? = null,

    @Json(name = "bloodTypeId")
    var bloodTypeId: Int? = null,

    @Json(name = "urgencTypeId")
    var urgencTypeId: Int? = null,

    @Json(name = "hospitalId")
    var hospitalId: String? = null,

    @Json(name = "donorId")
    var donorId: String? = null,

    @Json(name = "firstName")
    var firstName: String? = null,

    @Json(name = "lastName")
    var lastName: String? = null,

    @Json(name = "age")
    var age: String? = null,

    @Json(name = "quantity")
    var quantity: Double? = null,

    @Json(name = "postKey")
    var postKey: String? = null,

    @Json(name = "donationDate")
    var donationDate: Double? = null,

    @Json(name = "donationTime")
    var donationTime: Double? = null,

    )