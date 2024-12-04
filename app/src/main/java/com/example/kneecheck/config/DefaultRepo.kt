package com.example.kneecheck.config

import android.util.Log
import com.example.kneecheck.entity.BasicDRO
import com.example.kneecheck.entity.LoginDRO
import com.example.kneecheck.entity.loginDTO
import com.example.kneecheck.entity.registerDokterDTO
import com.example.kneecheck.entity.registerPasienDTO
import com.example.kneecheck.pasien.LandingPageResponse

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

    suspend fun getDashboard(token: String): Any{
        return dataSourceRemote.getDashboard(token)
    }

    suspend fun fetchLandingPage(): LandingPageResponse {
        return dataSourceRemote.getLandingPage()
    }
}