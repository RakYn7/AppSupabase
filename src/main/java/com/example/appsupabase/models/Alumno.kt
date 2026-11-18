package com.example.appsupabase.models

import kotlinx.serialization.Serializable

@Serializable
data class Alumno(
    val id: Int? = null,
    val created_at: String? = null,
    val nombres: String? = null,
    val paralelo: String? = null,
    val correo: String? = null,
    val telefono: String? = null,
    val foto: String? = null
)