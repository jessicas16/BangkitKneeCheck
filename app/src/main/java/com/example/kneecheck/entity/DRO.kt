package com.example.kneecheck.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BasicDRO(
    var status : String,
    var message: String,
    var data: Any?
)

data class ErrorDRO(
    var status: String,
    var message: String
)

data class BasicDMLDRO(
    var status : String,
    var message: String,
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
    val label : levelData
)

data class ageData(
    val average: String,
    val total: Int
)

data class genderData(
    val average : String,
    val total : Int
)

data class levelData(
    val normal : Int,
    val kneePain : Int,
    val severeKneePain : Int,
)

data class landingPageDRO(
    val status: String,
    val message: String,
    val data: landingPageData
)

data class landingPageData(
    val totalScanned: Int,
    val gender: genderDataLanding,
    val age: ageDataLanding
)

data class genderDataLanding(
    val average: String,
    val total_kasus: Int
)

data class ageDataLanding(
    val average: String,
    val total_kasus: Int
)