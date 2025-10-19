package com.example.juegodecolores

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

class ResultFragment : Fragment(R.layout.result_fragment) {

    private lateinit var adaptadorPuntajes: ScoreAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val puntajeFinal = arguments?.getInt("finalScore") ?: 0

        configurarPantalla(view, puntajeFinal)
        configurarLista(view)
        configurarBoton(view)
    }

    private fun configurarPantalla(view: View, puntajeFinal: Int) {
        val textoPuntajeFinal = view.findViewById<TextView>(R.id.finalScoreTextView)
        val textoMejorPuntaje = view.findViewById<TextView>(R.id.highScoreTextView)
        textoPuntajeFinal.text = getString(R.string.final_score_format, puntajeFinal)

        val mejorPuntaje = actualizarMejorPuntaje(puntajeFinal)
        textoMejorPuntaje.text = getString(R.string.high_score_format, mejorPuntaje)

    }

    private fun actualizarMejorPuntaje(nuevoPuntaje: Int): Int {
        val preferencias = requireActivity().getSharedPreferences("juego_prefs", 0)
        val mejorActual = preferencias.getInt("mejor_puntaje", 0)

        if (nuevoPuntaje > mejorActual) {
            preferencias.edit().putInt("mejor_puntaje", nuevoPuntaje).apply()
            return nuevoPuntaje
        }
        return mejorActual
    }

    private fun configurarLista(view: View) {
        val listaPuntajes = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.scoreHistoryRecyclerView)
        adaptadorPuntajes = ScoreAdapter(ScoreHistory.obtenerPuntajes())
        listaPuntajes.layoutManager = LinearLayoutManager(requireContext())
        listaPuntajes.adapter = adaptadorPuntajes
    }

    private fun configurarBoton(view: View) {
        val botonJugarOtraVez = view.findViewById<Button>(R.id.playAgainButton)
        botonJugarOtraVez.setOnClickListener {
            (activity as MainActivity).volverAlJuego()
        }
    }
}