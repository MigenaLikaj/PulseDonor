package com.pulsedonor.app.models.home

class HomePostsResponse(
    val posterName: String,
    val posterImage: String,
    val bloodGroup: String,
    val quantity: String,
    val urgency: String,
    val hospital: String,
    val dateTime: String,
    val bloodRecipientAge: Int,
    val bloodRecipientName: String
)