package com.example.juegodecolores

import android.content.Context
import android.media.MediaPlayer

class Sonido(private val context: Context) {

    // Efectos de sonido
    private var soundCorrect: MediaPlayer? = null
    private var soundWrong: MediaPlayer? = null

    fun playCorrectSound() {
        try {
            soundCorrect?.release()
            soundCorrect = MediaPlayer.create(context, R.raw.CorrectoSonido)
            soundCorrect?.start()
        } catch (e: Exception) {
            // Si hay error, no hacer nada
        }
    }

    fun playWrongSound() {
        try {
            soundWrong?.release()
            soundWrong = MediaPlayer.create(context, R.raw.ErrorSonido)
            soundWrong?.start()
        } catch (e: Exception) {
            // Ignorar errores de sonido
        }
    }

    fun releaseAll() {
        soundCorrect?.release()
        soundWrong?.release()
    }
}