package com.example.juegodecolores

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Empezar con el fragment de bienvenida
        if (savedInstanceState == null) {
            navegarAWelcomeFragment()
        }
    }

    fun navegarAGameFragment() {
        val fragment = GameFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun navegarAResultFragment(score: Int) {
        val fragment = ResultFragment().apply {
            arguments = Bundle().apply {
                putInt("finalScore", score)
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun navegarAWelcomeFragment() {
        val fragment = WelcomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    fun volverAlJuego() {
        // Regresa al fragment anterior (GameFragment)
        supportFragmentManager.popBackStack()
    }
}