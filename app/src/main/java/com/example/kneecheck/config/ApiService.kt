package com.example.kneecheck.config

import com.example.kneecheck.entity.BasicDMLDRO
import com.example.kneecheck.entity.BasicDRO
import com.example.kneecheck.entity.LoginDRO
import com.example.kneecheck.entity.dashboardDokter
import com.example.kneecheck.entity.landingPageDRO
import com.example.kneecheck.entity.loginDTO
import com.example.kneecheck.entity.predictDRO
import com.example.kneecheck.entity.registerDokterDTO
import com.example.kneecheck.entity.registerPasienDTO
import com.example.kneecheck.entity.updatePasswordPasienDTO
import com.example.kneecheck.entity.updateProfilePasienDTO
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Multipart
import retrofit2.http.Part

interface ApiService {
    @GET("users")
    suspend fun getAllUser() : BasicDRO

    @POST("login")
    suspend fun login(
        @Body user : loginDTO
    ): LoginDRO

    @POST("register/pasien")
    suspend fun registerPasien(
        @Body regPasien : registerPasienDTO
    ): Any

    @POST("register/dokter")
    suspend fun registerDokter(
        @Body regDokter : registerDokterDTO
    ): Any

    @GET("dashboard")
    suspend fun getDashboard(
        @Header("Authorization") token: String
    ): dashboardDokter

    @GET("home")
    suspend fun getLandingPage(
        @Header("Authorization") token: String
    ): landingPageDRO

    @PUT("/pasien/profile/update-profile")
    suspend fun updateProfilePasien(
        @Header("Authorization") token: String,
        @Body dataPasien : updateProfilePasienDTO
    ): BasicDMLDRO

    @PUT("/pasien/profile/update-user")
    suspend fun updatePasswordPasien(
        @Header("Authorization") token: String,
        @Body dataPasien : updatePasswordPasienDTO
    ): BasicDMLDRO

    @Multipart
    @POST("predict")
    suspend fun predict(
        @Header("Authorization") token: String,
        @Part img: MultipartBody.Part,
    ): predictDRO
}
