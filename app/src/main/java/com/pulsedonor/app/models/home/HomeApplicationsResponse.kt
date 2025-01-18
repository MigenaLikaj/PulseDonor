package com.pulsedonor.app.models.datas

import com.squareup.moshi.Json

class HomeApplicationsResponse(

    @Json(name = "data")
    var data: List<ApplicationData>? = null
)

class ApplicationData(

    @Json(name = "id")
    var id: Int? = null,

    @Json(name = "bloodRequestId")
    var bloodRequestId: Int? = null,

    @Json(name = "postKey")
    var postKey: String? = null,

    @Json(name = "quantity")
    var quantity: Double? = null,

    @Json(name = "urgence")
    var urgence: UrgenceData? = null,

    @Json(name = "bloodType")
    var bloodType: BloodTypeData? = null,

    @Json(name = "donationDate")
    var donationDate: String? = null,

    @Json(name = "donationTime")
    var donationTime: String? = null,

    @Json(name = "isAccepted")
    var isAccepted: Boolean? = null
)

class UrgenceData(

    @Json(name = "urgenceTypeId")
    var urgenceTypeId: Int? = null,

    @Json(name = "urgenceType")
    var urgenceType: String? = null
)

class BloodTypeData(

    @Json(name = "bloodTypeId")
    var bloodTypeId: Int? = null,

    @Json(name = "bloodType")
    var bloodType: String? = null
)
