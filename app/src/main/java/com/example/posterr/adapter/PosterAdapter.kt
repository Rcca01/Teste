package com.example.posterr.adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.posterr.R
import com.example.posterr.models.Poster

class PosterAdapter(
    private val posters: MutableList<Poster>,
    private val addComment: (position:Int) -> Unit
): RecyclerView.Adapter<PosterAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)
        val vh = VH(v)
        vh.layoutEvent.setOnClickListener {
            Log.d("ItemPoster", posters[vh.absoluteAdapterPosition].list.size.toString())
        }
        vh.btnComment.setOnClickListener {
            addComment(vh.absoluteAdapterPosition)
        }
        return vh
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val text = posters[position].text
        holder.textPoster.text = text
        val numComments = posters[position].list.size
        holder.tvNumComment.text =  "$numComments Comments"
        holder.tvNumRepost.text = "0 Repost"
    }

    override fun getItemCount(): Int = posters.size

    inner class VH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textPoster: TextView = itemView.findViewById(R.id.tvPoster)
        val layoutEvent: ConstraintLayout = itemView.findViewById(R.id.layoutEvent)
        val btnComment: ImageButton = itemView.findViewById(R.id.imComment)
        val btnRePost: ImageButton = itemView.findViewById(R.id.imRepost)
        val tvNumComment: TextView = itemView.findViewById(R.id.tvNumComment)
        val tvNumRepost: TextView = itemView.findViewById(R.id.tvNumRepost)
    }

}