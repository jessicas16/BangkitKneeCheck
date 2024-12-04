package com.example.kneecheck.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class loginDTO (
    val email: String,
    val password: String
)

data class registerPasienDTO(
    val name: String,
    val email: String,
    val password: String,
    val gender: String,
    val address: String,
    val birth: String,
)

data class registerDokterDTO(
    val name: String,
    val email: String,
    val password: String,
    val gender: String,
    val address: String,
    val hospital: String,
)

data class updateProfileDokterDTO(
    val nama: String,
    val jenisKelamin: String,
    val domisili: String,
    val instansi: String
)

data class updatePasswordPasienDTO(
    val email: String,
    val password: String,
    val verifyPassword: String
)

data class updateProfilePasienDTO(
    val name: String,
    val gender: String,
    val birth: String,
    val address: String
)