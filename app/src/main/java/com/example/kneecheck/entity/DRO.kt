package com.example.kneecheck.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BasicDRO(
    var status : String,
    var message: String,
    var data: Any?
)

data class LoginDRO(
    var token : String,
    var userType : String,
    var name: String,
    var id : String
)

data class dashboardDokter(
    val status: String,
    val message: String,
    val data: dashboardDokterData
)

data class dashboardDokterData(
    val age: ageData,
    val gender: genderData,
    val totalScanned : Int,
    val level : levelData
)

data class ageData(
    val age: Int,
    val total: Int
)

data class genderData(
    val gender : String,
    val total : Int
)

data class levelData(
    val normal : Int,
    val kneePain : Int,
    val severeKneePain : Int,
)