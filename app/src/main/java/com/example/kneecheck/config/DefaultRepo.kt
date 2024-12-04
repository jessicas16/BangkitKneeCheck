package com.example.kneecheck.config

import android.util.Log
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

class DefaultRepo (
    private val dataSourceRemote : ApiService,
) {
    suspend fun getAllUser() : BasicDRO {
        Log.e("DefaultRepo", dataSourceRemote.getAllUser().toString())
        return dataSourceRemote.getAllUser()
    }

    suspend fun login(user : loginDTO): LoginDRO {
        Log.e("DefaultRepo result", dataSourceRemote.login(user).toString())
        return dataSourceRemote.login(user)
    }

    suspend fun registerPasien(user : registerPasienDTO): Any{
        return dataSourceRemote.registerPasien(user)
    }

    suspend fun registerDokter(user : registerDokterDTO): Any{
        return dataSourceRemote.registerDokter(user)
    }

    suspend fun getDashboard(token: String): dashboardDokter{
        Log.e("DefaultRepo WOOEE", dataSourceRemote.getDashboard(token).toString())
        return dataSourceRemote.getDashboard(token)
    }

    suspend fun getLandingPage(token: String): landingPageDRO{
        return dataSourceRemote.getLandingPage(token)
    }

    suspend fun updateProfilePasien(token: String, data: updateProfilePasienDTO): BasicDMLDRO{
        return dataSourceRemote.updateProfilePasien(token, data)
    }

    suspend fun updatePasswordPasien(token: String, data: updatePasswordPasienDTO): BasicDMLDRO {
        return dataSourceRemote.updatePasswordPasien(token, data)
    }

    suspend fun predict(token: String, img: MultipartBody.Part): predictDRO {
        return dataSourceRemote.predict(token, img)
    }
}