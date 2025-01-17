package com.pulsedonor.app.utilities

import com.pulsedonor.app.events.ShowToastEvent
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
            EventBus.getDefault().post(ShowToastEvent("Something went wrong $statusCode"))

        } else {
            if (error.localizedMessage != null) {
                if (error.localizedMessage!!.contains("Unable")) {
                    EventBus.getDefault()
                        .post(ShowToastEvent("No internet connection!"))
                } else {
                    EventBus.getDefault().post(ShowToastEvent("Something went wrong"))

                }
            }
        }
    }
}

