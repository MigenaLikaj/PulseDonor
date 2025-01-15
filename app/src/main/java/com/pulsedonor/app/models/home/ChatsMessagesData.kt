package com.pulsedonor.app.models.home

data class ChatsMessagesData(
    val message: String, // The text content of the message
    val isSender: Boolean // True if the message was sent by the user, false if received
)
