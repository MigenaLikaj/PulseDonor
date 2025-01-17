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
import com.pulsedonor.app.models.profile.GetMyPostdetailsData
import com.pulsedonor.app.models.profile.JoinGroupBody
import com.pulsedonor.app.models.profile.MyPostsResponse
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //Account
    @GET("Account")
    fun getAccount(
        @Header("Authorization") bearerToken: String
    ): Observable<GetAccountResponse>

    @PUT("Account")
    fun editAccount(
        @Body editAccountBody: EditAccountBody,
        @Header("Authorization") bearerToken: String
    ): Observable<GeneralResponse>

    @GET("Account/user-profile-blood-requests")
    fun getUserProfileBloodRequests(
        @Header("Authorization") bearerToken: String
    ): Observable<MyPostsResponse>

    @POST("Account/blood-request") //mvyn responsi
    fun addBloodRequest(
        @Body addBloodRequestBody: AddEditBloodRequestBody,
        @Header("Authorization") bearerToken: String
    ): Observable<GeneralResponse>

    @GET("Account/blood-request-by-id")
    fun getBloodRequestByIdAC(
        @Query("id") id: Int,
        @Header("Authorization") bearerToken: String
    ): Observable<GetMyPostdetailsData>

    @PUT("Account/blood-request/{id}")
    fun editBloodRequestAC(
        @Path("id") id: Int,
        @Body editBloodRequestBody: AddEditBloodRequestBody,
        @Header("Authorization") bearerToken: String
    ): Observable<BloodRequestsResponse>

    @DELETE("Account/blood-request/{id}")
    fun deleteBloodRequestAC(
        @Path("id") id: Int,
        @Header("Authorization") bearerToken: String
    ): Observable<BloodRequestsResponse>

    @GET("Account/user-profile-applications")
    fun getUserProfileApplicationsAC(
        @Header("Authorization") bearerToken: String
    ): Observable<BloodRequestsResponse>

    @GET("Account/application/{id}")
    fun getApplicationsByIdAC(
        @Path("id") id: Int,
        @Header("Authorization") bearerToken: String
    ): Observable<BloodRequestsResponse>

    @DELETE("Account/application/{id}")
    fun deleteApplication(
        @Path("id") id: Int,
        @Header("Authorization") bearerToken: String
    ): Observable<BloodRequestsResponse>

    //auth
    @POST("Auth/signup")
    fun signUp(
        @Body signupBody: SignUpBody
    ): Observable<GeneralResponse>

    @POST("Auth/login")
    fun signIn(
        @Body signInBody: SignInBody
    ): Observable<ResponseBody>

    //bloodDonationPoints
    @GET("blood-donation-points")
    fun getBloodDonationPoints(
        @Header("Authorization") bearerToken: String
    ): Observable<GetAccountResponse>

    @GET("blood-donation-points/{id}")
    fun getBloodDonationPointsById(
        @Path("id") id: Int,
        @Header("Authorization") bearerToken: String
    ): Observable<GeneralResponseInt>

    //bloodrequests
    @POST("blood-request/add")
    fun addBloodRequest(
        @Body addBloodRequest: AddBloodRequest,
        @Header("Authorization") bearerToken: String
    ): Observable<GeneralResponseInt>

    @GET("blood-request")
    fun getBloodRequests(
        @Header("Authorization") bearerToken: String
    ): Observable<BloodRequestsResponse>

    @GET("blood-request/{id}")
    fun getBloodRequestById(
        @Path("id") id: Int,
        @Header("Authorization") bearerToken: String
    ): Observable<GeneralResponseInt>

    @PUT("blood-request/edit")
    fun editBloodRequest(
        @Body editBloodRequest: AddBloodRequest,
        @Header("Authorization") bearerToken: String
    ): Observable<GeneralResponseInt>

    @DELETE("blood-request/delete")
    fun deleteBloodRequest(
        @Query("id") id: Int,
        @Header("Authorization") bearerToken: String
    ): Observable<BloodRequestsResponse>


    //datas
    @GET("Data/blood-types")
    fun getBloodTypes(
        @Header("Authorization") bearerToken: String
    ): Observable<BloodTypesResponse>

    @GET("Data/blood-types")
    fun getCities(
        @Header("Authorization") bearerToken: String
    ): Observable<CitiesResponse>

    @GET("Data/urgence-types")
    fun getUrgencetypes(
        @Header("Authorization") bearerToken: String
    ): Observable<DatasResponse>

    @GET("Data/hospitals")
    fun getHospitals(
        @Header("Authorization") bearerToken: String
    ): Observable<DatasResponse>


    //hall of fame
    @GET("HallOfFame/top-three-donors")
    fun getTopThreeDonors(
        @Header("Authorization") bearerToken: String
    ): Observable<DatasResponse>

    @GET("HallOfFame/blood-types-chart")
    fun getBloodTypeCharts(
        @Header("Authorization") bearerToken: String
    ): Observable<DatasResponse>

    @POST("HallOfFame/group")
    fun group(
        @Body groupRequest: GroupRequest,
        @Header("Authorization") bearerToken: String
    ): Observable<GetAccountResponse>

    @POST("HallOfFame/group")
    fun addGroup(
        @Body addGroupBody: AddGroupBody,
        @Header("Authorization") bearerToken: String
    ): Observable<GeneralResponseInt>

    @POST("HallOfFame/group-join-code")
    fun groupJoinCode(
        @Query("groupId") groupId: Int,
        @Header("Authorization") bearerToken: String
    ): Observable<GeneralResponseInt>

    @POST("HallOfFame/join-group/{groupId}")
    fun joinGroup(
        @Path("groupId") groupId: Int,
        @Body joinGroupBody: JoinGroupBody,
        @Header("Authorization") bearerToken: String
    ): Observable<GeneralResponseInt>

    //OpenAI
    @GET("OpenAI/use-chat")
    fun useChat(
        @Query("query") query: String,
        @Header("Authorization") bearerToken: String
    ): Observable<GeneralResponse>

}