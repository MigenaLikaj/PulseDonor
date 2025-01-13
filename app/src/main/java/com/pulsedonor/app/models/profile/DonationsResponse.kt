package com.pulsedonor.app.models.profile


data class DonationsResponse(
    val bloodGroup: String,
    val quantity: String,
    val urgency: String,
    val hospital: String,
    val dateTime: String
)
