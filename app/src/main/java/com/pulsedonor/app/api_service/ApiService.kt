package com.pulsedonor.app.api_service

import com.pulsedonor.app.models.AddBloodRequest
import com.pulsedonor.app.models.auth.GeneralResponse
import com.pulsedonor.app.models.auth.GeneralResponseInt
import com.pulsedonor.app.models.auth.SignInBody
import com.pulsedonor.app.models.auth.SignUpBody
import com.pulsedonor.app.models.datas.BloodTypesResponse
import com.pulsedonor.app.models.datas.CitiesResponse
import com.pulsedonor.app.models.datas.DatasResponse
import com.pulsedonor.app.models.hall_of_fame.GroupRequest
import com.pulsedonor.app.models.home.BloodRequestsResponse
import com.pulsedonor.app.models.profile.AddEditBloodRequestBody
import com.pulsedonor.app.models.profile.AddGroupBody
import com.pulsedonor.app.models.profile.EditAccountBody
import com.pulsedonor.app.models.profile.GetAccountResponse
import com.pulsedonor.app.models.profile.JoinGroupBody
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //Account
    @GET("Account")
    fun getAccount(): Observable<GetAccountResponse>

    @POST("Account")
    fun editAccount(
        @Body editAccountBody: EditAccountBody
    ): Observable<GeneralResponse>

    @GET("Account/user-profile-blood-requests")   //mvyn responsi
    fun getUserProfileBloodRequests(
    ): Observable<BloodRequestsResponse>

    @POST("Account/blood-request") //mvyn responsi
    fun addBloodRequest(
        @Body addBloodRequestBody: AddEditBloodRequestBody
    ): Observable<GeneralResponse>

    @GET("Account/blood-request-by-id")
    fun getBloodRequestByIdAC(
        @Query("id") id: Int
    ): Observable<BloodRequestsResponse>

    @PUT("Account/blood-request/{id}")
    fun editBloodRequestAC(
        @Path("id") id: Int,
        @Body editBloodRequestBody: AddEditBloodRequestBody
    ): Observable<BloodRequestsResponse>

    @DELETE("Account/blood-request/{id}")
    fun deleteBloodRequestAC(
        @Path("id") id: Int,
    ): Observable<BloodRequestsResponse>

    @GET("Account/user-profile-applications")
    fun getUserProfileApplicationsAC(
    ): Observable<BloodRequestsResponse>

    @GET("Account/application/{id}")
    fun getApplicationsByIdAC(
        @Path("id") id: Int
    ): Observable<BloodRequestsResponse>

    @DELETE("Account/application/{id}")
    fun deleteApplication(
        @Path("id") id: Int,
    ): Observable<BloodRequestsResponse>

    //auth
    @POST("Auth/signup")
    fun signUp(
        @Body signupBody: SignUpBody
    ): Observable<GeneralResponse>

    @POST("Auth/login")
    fun signIn(
        @Body signInBody: SignInBody
    ): Observable<GeneralResponse>

    //bloodDonationPoints
    @GET("blood-donation-points")
    fun getBloodDonationPoints(): Observable<GetAccountResponse>

    @GET("blood-donation-points/{id}")
    fun getBloodDonationPointsById(
        @Path("id") id: Int
    ): Observable<GeneralResponseInt>

    //bloodrequests
    @POST("blood-request/add")
    fun addBloodRequest(
        @Body addBloodRequest: AddBloodRequest
    ): Observable<GeneralResponseInt>

    @GET("blood-request")
    fun getBloodRequests(
    ): Observable<BloodRequestsResponse>

    @GET("blood-request/{id}")
    fun getBloodRequestById(
        @Path("id") id: Int
    ): Observable<GeneralResponseInt>

    @PUT("blood-request/edit")
    fun editBloodRequest(
        @Body editBloodRequest: AddBloodRequest
    ): Observable<GeneralResponseInt>

    @DELETE("blood-request/delete")
    fun deleteBloodRequest(
        @Query("id") id: Int,
    ): Observable<BloodRequestsResponse>


    //datas
    @GET("Data/blood-types")
    fun getBloodTypes(
    ): Observable<BloodTypesResponse>

    @GET("Data/blood-types")
    fun getCities(
    ): Observable<CitiesResponse>

    @GET("Data/urgence-types")
    fun getUrgencetypes(
    ): Observable<DatasResponse>

    @GET("Data/hospitals")
    fun getHospitals(
    ): Observable<DatasResponse>


    //hall of fame
    @GET("HallOfFame/top-three-donors")
    fun getTopThreeDonors(
    ): Observable<DatasResponse>

    @GET("HallOfFame/blood-types-chart")
    fun getBloodTypeCharts(
    ): Observable<DatasResponse>

    @POST("HallOfFame/group")
    fun group(
        @Body groupRequest: GroupRequest
    ): Observable<GetAccountResponse>

    @POST("HallOfFame/group")
    fun addGroup(
        @Body addGroupBody: AddGroupBody
    ): Observable<GeneralResponseInt>

    @POST("HallOfFame/group-join-code")
    fun groupJoinCode(
        @Query("groupId") groupId: Int
    ): Observable<GeneralResponseInt>

    @POST("HallOfFame/join-group/{groupId}")
    fun joinGroup(
        @Path("groupId") groupId: Int,
        @Body joinGroupBody: JoinGroupBody
    ): Observable<GeneralResponseInt>

    //OpenAI
    @GET("OpenAI/use-chat")
    fun useChat(
        @Query("query") query: String
    ): Observable<GeneralResponse>

}