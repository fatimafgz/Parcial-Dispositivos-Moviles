package com.example.juegodecolores

object ScoreHistory {
    private val puntajes = mutableListOf<Int>()

    fun agregarPuntaje(puntaje: Int) {
        puntajes.add(0, puntaje) // Agregar al inicio para mostrar los más recientes primero
        // Mantener solo los últimos 10 puntajes
        if (puntajes.size > 10) {
            puntajes.removeAt(puntajes.size - 1)
        }
    }

    fun obtenerPuntajes(): List<Int> {
        return puntajes.toList()
    }

    fun limpiarPuntajes() {
        puntajes.clear()
    }
}