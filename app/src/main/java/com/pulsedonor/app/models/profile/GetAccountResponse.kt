package com.pulsedonor.app.models.profile

import com.squareup.moshi.Json

class GetAccountResponse(

    @Json(name = "data")
    var data: GetAccountData? = null
)

class GetAccountData(

    @Json(name = "firstName")
    var firstName: String? = null,

    @Json(name = "lastName")
    var lastName: String? = null,

    @Json(name = "bloodType")
    var bloodType: BloodTypeData? = null,

    @Json(name = "primaryCity")
    var primaryCity: PrimaryCityData? = null,

    @Json(name = "secondaryCities")
    var secondaryCities: List<SecondaryCitiesData>? = null,

    @Json(name = "status")
    var status: Boolean? = null

)

class BloodTypeData(

    @Json(name = "bloodTypeId")
    var bloodTypeId: Int? = null,

    @Json(name = "type")
    var type: String? = null
)

class PrimaryCityData(

    @Json(name = "primaryCityId")
    var primaryCityId: Int? = null,

    @Json(name = "name")
    var name: String? = null
)

class SecondaryCitiesData(

    @Json(name = "secondaryCityId")
    var secondaryCityId: Int? = null,

    @Json(name = "name")
    var name: String? = null

)