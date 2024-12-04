package com.pulsedonor.app.data

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferences @Inject constructor(private val context: Context) {
    val PREFS_FILENAME = "com.pulsedonor.app"
    val TOKEN = "token"
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)


    var token: String?
        get() = sharedPreferences.getString(TOKEN, null)
        set(value) = sharedPreferences.edit().putString(TOKEN, value).apply()

//    fun start() {
//        val shared = AppPreferences.getShared(context)
//
//        val userId = shared.getString(userIdKey, "")
//        val token = shared.getString(userTokenKey, null)
//
//        val job = GlobalScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
//            user =   userDao.getUserById(userId!!)
//            this.coroutineContext.cancel()
//        }
//        this.token = token
//
//    }
}