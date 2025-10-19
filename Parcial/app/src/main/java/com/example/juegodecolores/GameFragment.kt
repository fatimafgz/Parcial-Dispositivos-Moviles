package com.example.juegodecolores

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class GameFragment : Fragment(R.layout.game_fragment) {

    private var score = 0
    private var tiempoRestante = 30000L
    private var temporizador: CountDownTimer? = null
    private var juegoActivo = true
    private lateinit var sonido: Sonido // Administrador de sonidos

    private val colores = listOf("ROJO", "VERDE", "AZUL", "AMARILLO", "MORADO", "NARANJA")
    private var colorActual = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar sonidos
        sonido = Sonido(requireContext())

        empezarJuego(view)
        configurarBotones(view)
        empezarTemporizador(view)
    }

    private fun configurarBotones(view: View) {
        view.findViewById<Button>(R.id.redButton).setOnClickListener {
            verificarRespuesta("ROJO", view)
        }
        view.findViewById<Button>(R.id.greenButton).setOnClickListener {
            verificarRespuesta("VERDE", view)
        }
        view.findViewById<Button>(R.id.blueButton).setOnClickListener {
            verificarRespuesta("AZUL", view)
        }
        view.findViewById<Button>(R.id.yellowButton).setOnClickListener {
            verificarRespuesta("AMARILLO", view)
        }
        view.findViewById<Button>(R.id.purpleButton).setOnClickListener {
            verificarRespuesta("MORADO", view)
        }
        view.findViewById<Button>(R.id.orangeButton).setOnClickListener {
            verificarRespuesta("NARANJA", view)
        }
    }

    private fun verificarRespuesta(colorSeleccionado: String, view: View) {
        if (!juegoActivo) return

        if (colorSeleccionado == colorActual) {
            // Correcto
            score++
            actualizarPuntaje(view)
            generarNuevoColor(view)
            sonido.playCorrectSound()
        } else {
            // Incorrecto
            sonido.playWrongSound()
        }
    }

    private fun empezarTemporizador(view: View) {
        val textoContador = view.findViewById<TextView>(R.id.timerTextView)

        temporizador = object : CountDownTimer(tiempoRestante, 1000) {
            override fun onTick(milisRestantes: Long) {
                tiempoRestante = milisRestantes
                val segundos = milisRestantes / 1000
                textoContador.text = getString(R.string.time_format, segundos)
            }

            override fun onFinish() {
                terminarJuego()
            }
        }.start()
    }

    private fun generarNuevoColor(view: View) {
        val colorAleatorio = Random.nextInt(colores.size)
        colorActual = colores[colorAleatorio]

        val vistaColor = view.findViewById<View>(R.id.targetColorView)

        when (colorActual) {
            "ROJO" -> vistaColor.setBackgroundColor(resources.getColor(R.color.color_red, null))
            "VERDE" -> vistaColor.setBackgroundColor(resources.getColor(R.color.color_green, null))
            "AZUL" -> vistaColor.setBackgroundColor(resources.getColor(R.color.color_blue, null))
            "AMARILLO" -> vistaColor.setBackgroundColor(resources.getColor(R.color.color_yellow, null))
            "MORADO" -> vistaColor.setBackgroundColor(resources.getColor(R.color.color_purple, null))
            "NARANJA" -> vistaColor.setBackgroundColor(resources.getColor(R.color.color_orange, null))
        }
    }

    private fun actualizarPuntaje(view: View) {
        val textoPuntaje = view.findViewById<TextView>(R.id.scoreTextView)
        textoPuntaje.text = getString(R.string.score_format, score)
    }

    private fun empezarJuego(view: View) {
        score = 0
        actualizarPuntaje(view)
        generarNuevoColor(view)
    }

    private fun terminarJuego() {
        juegoActivo = false
        temporizador?.cancel()

        // Guardar el puntaje
        ScoreHistory.agregarPuntaje(score)

        // Ir a la pantalla de resultados
        (activity as MainActivity).navegarAResultFragment(score)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        temporizador?.cancel()
        sonido.releaseAll() // Liberar recursos de sonido
    }
}