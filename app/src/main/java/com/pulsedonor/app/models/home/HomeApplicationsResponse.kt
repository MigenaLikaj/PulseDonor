package com.pulsedonor.app.models.home

class HomeApplicationsResponse(
    val id: Int,
    val bloodGroup: String,
    val quantity: String,
    val urgency: String,
    val dateTime: String,
    val statustype: Int
)