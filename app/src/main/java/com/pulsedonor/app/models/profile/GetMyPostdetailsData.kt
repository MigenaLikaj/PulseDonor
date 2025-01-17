package com.pulsedonor.app.models.profile

import com.squareup.moshi.Json

class GetMyPostdetailsData(

    @Json(name = "data")
    var data: PostDetailsData? = null,

    )

class PostDetailsData(

    @Json(name = "id")
    var id: Int? = null,

    @Json(name = "bloodType")
    var bloodType: BloodTypeDetails? = null,

    @Json(name = "quantity")
    var quantity: Double? = null,

    @Json(name = "urgenceType")
    var urgenceType: UrgenceTypeDetails? = null,

    @Json(name = "hospital")
    var hospital: HospitalDetails? = null,

    @Json(name = "donationDate")
    var donationDate: String? = null,

    @Json(name = "donationTime")
    var donationTime: String? = null,

    @Json(name = "firstname")
    var firstname: String? = null,

    @Json(name = "lastName")
    var lastName: String? = null,

    @Json(name = "age")
    var age: Int? = null,
)

class BloodTypeDetails(
    @Json(name = "bloodTypeId")
    var bloodTypeId: Int? = null,

    @Json(name = "type")
    var type: String? = null,
)

class UrgenceTypeDetails(
    @Json(name = "urgenceTypeId")
    var urgenceTypeId: Int? = null,

    @Json(name = "type")
    var type: String? = null,
)

class HospitalDetails(
    @Json(name = "hospitalId")
    var hospitalId: Int? = null,

    @Json(name = "name")
    var name: String? = null,
)