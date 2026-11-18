package com.example.appsupabase.utils

import java.net.UnknownHostException

object SupabaseErrorHandler {
    fun getErrorMessage(e: Exception): String {
        return when (e) {
            is UnknownHostException -> "Sin conexión a internet. Verifica tu red."
            else -> e.localizedMessage ?: "Error desconocido al conectar con Supabase."
        }
    }
}