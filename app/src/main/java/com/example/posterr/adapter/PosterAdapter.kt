package com.example.posterr.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.posterr.R
import com.example.posterr.models.Poster

class PosterAdapter(
    private val posters: MutableList<Poster>
): RecyclerView.Adapter<PosterAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)
        val vh = VH(v)
        vh.itemView.setOnClickListener {
            Log.d("TESTING", "Poster clicked")
        }
        return vh
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val text = posters[position].text
        holder.textPoster.text = text
    }

    override fun getItemCount(): Int = posters.size

    inner class VH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textPoster: TextView = itemView.findViewById(R.id.tvPoster)
    }

}