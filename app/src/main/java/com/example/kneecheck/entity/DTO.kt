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

data class updatePasswordPasienDTO(
    val email: String,
    val password: String,
    val verifyPassword: String
)

data class updatePasswordDokterDTO(
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

data class updateProfileDokterDTO(
    val name: String,
    val gender: String,
    val hospital: String,
    val address: String
)