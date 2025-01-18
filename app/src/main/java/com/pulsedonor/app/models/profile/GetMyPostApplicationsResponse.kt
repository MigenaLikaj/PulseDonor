package com.pulsedonor.app.models.profile

import com.squareup.moshi.Json

class GetMyPostApplicationsResponse(

    @Json(name = "data")
    var data: List<PostApplicationsData>? = null
)

class PostApplicationsData(

    @Json(name = "id")
    var id: Int? = null,

    @Json(name = "bloodRequestId")
    var bloodRequestId: Int? = null,

    @Json(name = "fullname")
    var fullname: String? = null,

    @Json(name = "phoneNumber")
    var phoneNumber: String? = null,

    @Json(name = "email")
    var email: String? = null,

    @Json(name = "bloodType")
    var bloodType: String? = null,

    @Json(name = "isAccepted")
    var isAccepted: Boolean? = null,

    @Json(name = "canConfirm")
    var canConfirm: Boolean? = null
)