package com.pulsedonor.app.models.profile

import com.squareup.moshi.Json

class MyPostsResponse(

    @Json(name = "data")
    var data: List<MyPostData>? = null
)

class MyPostData(

    @Json(name = "id")
    var id: Int? = null,

    @Json(name = "bloodType")
    var bloodType: String? = null,

    @Json(name = "quantity")
    var quantity: Int? = null,

    @Json(name = "urgenceType")
    var urgenceType: UrgenceTypeData? = null,

    @Json(name = "donationDate")
    var donationDate: String? = null,

    @Json(name = "donationTime")
    var donationTime: String? = null,

    @Json(name = "hospital")
    var hospital: HospitalData? = null,

    @Json(name = "numberOfApplications")
    var numberOfApplications: Int? = null,

    )

class UrgenceTypeData(
    @Json(name = "id")
    var id: Int? = null,

    @Json(name = "type")
    var type: String? = null,
)

class HospitalData(
    @Json(name = "id")
    var id: Int? = null,

    @Json(name = "name")
    var name: String? = null,
)