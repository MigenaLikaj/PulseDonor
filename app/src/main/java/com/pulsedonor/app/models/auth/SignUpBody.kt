package com.pulsedonor.app.models.auth

import com.squareup.moshi.Json

class SignUpBody(

    @Json(name = "signupDto")
    var signupDto: SignupDtoResponse? = null
)

class SignupDtoResponse(

    @Json(name = "userName")
    var userName: String? = null,

    @Json(name = "firstName")
    var firstName: String? = null,

    @Json(name = "lastName")
    var lastName: String? = null,

    @Json(name = "email")
    var email: String? = null,

    @Json(name = "phoneNumber")
    var phoneNumber: String? = null,

    @Json(name = "password")
    var password: String? = null,

    @Json(name = "genderId")
    var genderId: Int? = null,

    @Json(name = "bloodTypeId")
    var bloodTypeId: Int? = null
)