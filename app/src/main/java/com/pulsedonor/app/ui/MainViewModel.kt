package com.pulsedonor.app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pulsedonor.app.api_service.ApiService
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.models.auth.GeneralResponse
import com.pulsedonor.app.models.auth.SignInBody
import com.pulsedonor.app.models.auth.SignUpBody
import com.pulsedonor.app.models.auth.SignupDtoResponse
import com.pulsedonor.app.models.datas.BloodTypesResponse
import com.pulsedonor.app.models.datas.CitiesResponse
import com.pulsedonor.app.models.profile.EditAccountBody
import com.pulsedonor.app.models.profile.GetAccountResponse
import com.pulsedonor.app.utilities.errorHandle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val appPreferences: AppPreferences,
    val apiService: ApiService
) : ViewModel() {

    lateinit var disposable: Disposable
    var loading = MutableLiveData<Boolean>()
    var successSignUp = MutableLiveData<GeneralResponse>()
    var successSignIn = MutableLiveData<GeneralResponse>()
    var getBloodTypes = MutableLiveData<BloodTypesResponse>()
    var getCities = MutableLiveData<CitiesResponse>()
    var getUserProfile = MutableLiveData<GetAccountResponse>()
    val dataSavedSuccessfully = MutableLiveData<Boolean>()

    fun signUp(
        userName: String,
        firstname: String,
        lastname: String,
        password: String,
        email: String,
        genderId: Int,
        bloodTypeId: Int,
    ) {
        disposable = apiService.signUp(
            SignUpBody(
                SignupDtoResponse(
                    userName = userName,
                    firstName = firstname,
                    lastName = lastname,
                    password = password,
                    email = email,
                )
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { loading.value = false }
            .doOnSubscribe { loading.value = true }
            .subscribe({ result ->
                successSignUp.value = result
            }, { error ->
                error.printStackTrace()
                errorHandle(error)
            })
    }

    fun signIn(
        email: String,
        password: String
    ) {
        disposable = apiService.signIn(
            SignInBody(
                email = email,
                password = password
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { loading.value = false }
            .doOnSubscribe { loading.value = true }
            .subscribe({ result ->
                successSignIn.value = result
            }, { error ->
                error.printStackTrace()
                errorHandle(error)
            })
    }

    fun getBloodTypes() {
        disposable = apiService.getBloodTypes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { loading.value = false }
            .doOnSubscribe { loading.value = true }
            .subscribe({ result ->
                getBloodTypes.value = result
            }, { error ->
                error.printStackTrace()
                errorHandle(error)
            })
    }

    fun getCities() {
        disposable = apiService.getCities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { loading.value = false }
            .doOnSubscribe { loading.value = true }
            .subscribe({ result ->
                getCities.value = result
            }, { error ->
                error.printStackTrace()
                errorHandle(error)
            })
    }

    fun getUserProfile() {
        disposable = apiService.getAccount()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { loading.value = false }
            .doOnSubscribe { loading.value = true }
            .subscribe({ result ->
                getUserProfile.value = result
            }, { error ->
                error.printStackTrace()
                errorHandle(error)
            })
    }

    fun editAccount(
        firstName: String,
        lastName: String,
        email: String,
        bloodTypeId: Int,
        primaryCityId: Int,
    ) {
        disposable = apiService.editAccount(
            EditAccountBody(
                firstName = firstName,
                lastName = lastName,
                email = email,
                bloodTypeId = bloodTypeId,
                primaryCityId = primaryCityId
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { loading.value = false }
            .doOnSubscribe { loading.value = true }
            .subscribe({ result ->
                dataSavedSuccessfully.value = true
            }, { error ->
                error.printStackTrace()
                errorHandle(error)
            })
    }

}