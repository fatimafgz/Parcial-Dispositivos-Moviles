package com.example.juegodecolores

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class WelcomeFragment : Fragment(R.layout.welcome_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pantalla cargada, solo conectamos los botones
        val startButton = view.findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            (activity as MainActivity).navegarAGameFragment()
        }

        mostrarReglasDelJuego()
    }

    private fun mostrarReglasDelJuego() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Cómo jugar")
            .setMessage("• Tienes 30 segundos\n• Toca el botón del color que ves arriba\n• Cada acierto suma 1 punto\n• ¡Consigue la mayor puntuación!")
            .setPositiveButton("¡A jugar!") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}