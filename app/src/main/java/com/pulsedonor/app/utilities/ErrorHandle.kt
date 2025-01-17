package com.pulsedonor.app.utilities

import org.greenrobot.eventbus.EventBus
import retrofit2.HttpException
import java.io.IOException

fun errorHandle(error: Throwable?) {
    //getting response body if status is not 200
    if (error != null) {
        error.printStackTrace()
        if (error is HttpException) {
            val httpException = error as HttpException
            var errorBody = ""
            try {
                errorBody = httpException.response()?.errorBody()!!.string()
            } catch (e: IOException) {
                e.printStackTrace()
//                FirebaseCrashlytics.getInstance().recordException(e)
            }

            val statusCode = httpException.code()

            if (statusCode == 401) {
//                EventBus.getDefault().post(PerformLogoutEvent())
            } else if (statusCode == 500) {
//                EventBus.getDefault().post(ShowToastEvent("Something went wrong" + "500"))
            } else {
//                val errorHandle = Gson().fromJson(errorBody, ServerResponse::class.java)
//                if (errorHandle != null) {
//                    for (i in errorHandle.errors!!.indices) {
//                        val message = errorHandle.errors!![0].message
//                        if (message != null) {
//                            EventBus.getDefault().post(ShowToastEvent(message))
//                        }
//
//                        val code = errorHandle.errors!![i].code
//                        if (code == "blocked_account") {
//                            EventBus.getDefault().post(PerformLogoutEvent())
//                        }
//                    }
//                } else {
////                    EventBus.getDefault()
////                        .post(ShowToastEvent(App.instance.getString(R.string.something_went_wrong) + error.message()))
//                }
            }
        } else {
            if (error.localizedMessage != null) {
                if (error.localizedMessage!!.contains("Unable")) {
                    EventBus.getDefault()
//                        .post(ShowToastEvent(App.instance.getString(R.string.no_internet_connection)))
                } else {
                    EventBus.getDefault()
//                        .post(ShowToastEvent(App.instance.getString(R.string.something_went_wrong) + error.localizedMessage))
                }
            }
        }
    }
}

