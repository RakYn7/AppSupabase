package com.example.appsupabase.data

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseManager {
    val client = createSupabaseClient(
        supabaseUrl = "https://xpqfbegmspildvauinlh.supabase.co",
        supabaseKey = "sb_publishable_yxLTg9L913FIyF2r015oig_7ksmXC3A"
    ) {
        install(Postgrest)
    }
}