package com.pulsedonor.app.api_service

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface ApiService {

    @GET("fr-FR/Data/GetSportActivities")
    fun getCountries(): Observable<ResponseBody>
}