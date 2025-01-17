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
import com.pulsedonor.app.models.datas.DatasResponse
import com.pulsedonor.app.models.profile.AddEditBloodRequestBody
import com.pulsedonor.app.models.profile.EditAccountBody
import com.pulsedonor.app.models.profile.GetAccountResponse
import com.pulsedonor.app.models.profile.GetMyPostdetailsData
import com.pulsedonor.app.models.profile.MyPostsResponse
import com.pulsedonor.app.utilities.errorHandle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val appPreferences: AppPreferences,
    val apiService: ApiService
) : ViewModel() {
    val bearerToken = "${appPreferences.token}"
    lateinit var disposable: Disposable
    var loading = MutableLiveData<Boolean>()
    var successSignUp = MutableLiveData<GeneralResponse>()
    var successSignIn = MutableLiveData<Boolean>()
    var getBloodTypes = MutableLiveData<BloodTypesResponse>()
    var getCities = MutableLiveData<CitiesResponse>()
    var getUrgencetypes = MutableLiveData<DatasResponse>()
    var getHospitals = MutableLiveData<DatasResponse>()
    val dataSavedSuccessfully = MutableLiveData<Boolean>()
    var getUserProfile = MutableLiveData<GetAccountResponse>()
    var getUserProfileBloodRequests = MutableLiveData<MyPostsResponse>()
    var getBloodRequestByIdAC = MutableLiveData<GetMyPostdetailsData>()
    var getChatResponse = MutableLiveData<GeneralResponse>()

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
                    genderId = genderId,
                    bloodTypeId = bloodTypeId,
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
                successSignIn.value = true
                val token = result.string()
                appPreferences.token = token
                println("Tokeni ${appPreferences.token}")
            }, { error ->
                error.printStackTrace()
                errorHandle(error)
            })
    }

    fun getBloodTypes() {
        disposable = apiService.getBloodTypes(bearerToken)
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
        disposable = apiService.getCities(bearerToken)
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

    fun getHospitals() {
        disposable = apiService.getHospitals(bearerToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { loading.value = false }
            .doOnSubscribe { loading.value = true }
            .subscribe({ result ->
                getHospitals.value = result
            }, { error ->
                error.printStackTrace()
                errorHandle(error)
            })
    }

    fun getUrgencetypes() {
        disposable = apiService.getUrgencetypes(bearerToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { loading.value = false }
            .doOnSubscribe { loading.value = true }
            .subscribe({ result ->
                getUrgencetypes.value = result
            }, { error ->
                error.printStackTrace()
                errorHandle(error)
            })
    }

    fun getUserProfile() {
        disposable = apiService.getAccount(bearerToken)
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
            ),
            bearerToken
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

    fun getUserProfileBloodRequests() {
        disposable = apiService.getUserProfileBloodRequests(bearerToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { loading.value = false }
            .doOnSubscribe { loading.value = true }
            .subscribe({ result ->
                getUserProfileBloodRequests.value = result
            }, { error ->
                error.printStackTrace()
                errorHandle(error)
            })
    }

    fun getBloodRequestByIdAC(id: Int) {
        disposable = apiService.getBloodRequestByIdAC(id, bearerToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { loading.value = false }
            .doOnSubscribe { loading.value = true }
            .subscribe({ result ->
                getBloodRequestByIdAC.value = result
            }, { error ->
                error.printStackTrace()
                errorHandle(error)
            })
    }

    fun editBloodRequestByIdAC(
        bloodTypeId: Int,
        quantity: Double,
        urgenceTypeid: Int,
        hospitalId: Int,
        donationDate: String,
        donationTime: String,
        age: Int,
        postId: Int
    ) {
        disposable = apiService.editBloodRequestAC(
            postId,
            AddEditBloodRequestBody(
                bloodTypeId = bloodTypeId,
                quantity = quantity,
                urgenceTypeId = urgenceTypeid,
                hospitalId = hospitalId,
                donationDate = donationDate,
                donationTime = donationDate,
                age = age
            ), bearerToken
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

    fun useChat(text: String) {
        disposable = apiService.useChat(text, bearerToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { loading.value = false }
            .doOnSubscribe { loading.value = true }
            .subscribe({ result ->
                getChatResponse.value = result
            }, { error ->
                error.printStackTrace()
                errorHandle(error)
            })
    }


}