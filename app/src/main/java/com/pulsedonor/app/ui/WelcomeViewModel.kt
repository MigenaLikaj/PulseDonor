package com.pulsedonor.app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pulsedonor.app.api_service.ApiService
import com.pulsedonor.app.data.AppPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(val appPreferences: AppPreferences, val apiService: ApiService) : ViewModel() {
    lateinit var disposable: Disposable
    var loading = MutableLiveData<Boolean>()

    fun getUserSubscription() {
        disposable = apiService.getCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { loading.value = false }
            .doOnSubscribe { loading.value = true }
            .subscribe({ result ->
                println("teeest $result")
            }, { error ->
                error.printStackTrace()
//                errorHandle(error)
            })
    }


}