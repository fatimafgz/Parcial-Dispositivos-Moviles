package com.example.juegodecolores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ScoreAdapter(private val scores: List<Int>) :
    RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    class ScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val scoreNumber: TextView = itemView.findViewById(R.id.scoreNumberTextView)
        val scoreValue: TextView = itemView.findViewById(R.id.scoreValueTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_score, parent, false)
        return ScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val score = scores[position]
        holder.scoreNumber.text = holder.itemView.context.getString(
            R.string.score_number_format,
            position + 1
        )
    }

    override fun getItemCount(): Int {
        return scores.size
    }
}