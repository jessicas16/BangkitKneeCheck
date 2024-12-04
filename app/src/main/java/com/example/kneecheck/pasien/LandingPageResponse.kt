package com.example.kneecheck.pasien

data class LandingPageResponse(
    val totalScanned: Int,
    val gender: GenderData,
    val age: AgeData
)

data class GenderData(
    val average: Double,
    val total_kasus: Int
)

data class AgeData(
    val average: Double,
    val total_kasus: Int
)

