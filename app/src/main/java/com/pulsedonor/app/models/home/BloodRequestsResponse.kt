package com.pulsedonor.app.models.home

import com.squareup.moshi.Json

class BloodRequestsResponse(

    @Json(name = "data")
    var data: List<BloodRequestsData>? = null
)

class BloodRequestsData(

    @Json(name = "id")
    var id: Int? = null,

    @Json(name = "author")
    var author: AuthorData? = null,

    @Json(name = "bloodType")
    var bloodType: BloodTypes? = null,

    @Json(name = "urgenceType")
    var urgenceType: UrgenceTypes? = null,

    @Json(name = "hospital")
    var hospital: Hospital? = null,

    @Json(name = "firstName")
    var firstName: String? = null,

    @Json(name = "lastName")
    var lastName: String? = null,

    @Json(name = "age")
    var age: Int? = null,

    @Json(name = "quantity")
    var quantity: Double? = null,

    @Json(name = "postKey")
    var postKey: String? = null,

    @Json(name = "donationDate")
    var donationDate: String? = null,

    @Json(name = "donationTime")
    var donationTime: String? = null,

    )

class AuthorData(
    @Json(name = "id")
    var id: String? = null,

    @Json(name = "name")
    var name: String? = null
)

class BloodTypes(
    @Json(name = "id")
    var id: Int? = null,

    @Json(name = "type")
    var type: String? = null
)

class UrgenceTypes(
    @Json(name = "id")
    var id: Int? = null,

    @Json(name = "type")
    var type: String? = null
)

class Hospital(
    @Json(name = "id")
    var id: Int? = null,

    @Json(name = "type")
    var name: String? = null
)