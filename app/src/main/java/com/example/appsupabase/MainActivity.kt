package com.example.appsupabase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.appsupabase.data.SupabaseManager
import com.example.appsupabase.models.Alumno
import com.example.appsupabase.utils.SupabaseErrorHandler
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var btnCargar: Button
    private lateinit var etResultados: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnCargar = findViewById(R.id.btnCargar)
        etResultados = findViewById(R.id.etResultados)

        btnCargar.setOnClickListener {
            cargarAlumnos()
        }
    }

    private fun cargarAlumnos() {
        lifecycleScope.launch {
            try {
                etResultados.setText("Cargando alumnos...")
                val listaAlumnos = SupabaseManager.client
                    .from("alumnos")
                    .select {
                        order("nombres", order = Order.ASCENDING)
                    }
                    .decodeList<Alumno>()

                if (listaAlumnos.isEmpty()) {
                    etResultados.setText("No hay alumnos registrados en la base de datos.")
                } else {
                    val texto = listaAlumnos.joinToString("\n\n") { alumno ->
                        "Nombre: ${alumno.nombres ?: "-"}\n" +
                                "Paralelo: ${alumno.paralelo ?: "-"}\n" +
                                "Teléfono: ${alumno.telefono ?: "-"}\n" +
                                "Correo: ${alumno.correo ?: "-"}"
                    }
                    etResultados.setText(texto)
                }
            } catch (e: Exception) {
                val mensajeError = SupabaseErrorHandler.getErrorMessage(e)
                etResultados.setText("Error: $mensajeError")
                Toast.makeText(this@MainActivity, mensajeError, Toast.LENGTH_LONG).show()
            }
        }
    }
}
