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
    private val openListComments: (position:Int) -> Unit,
    private val addComment: (position:Int) -> Unit,
    private val rePost: (message: String, position:Int) -> Unit
): RecyclerView.Adapter<PosterAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)
        val vh = VH(v)
        vh.layoutEvent.setOnClickListener {
            openListComments(vh.absoluteAdapterPosition)
        }
        vh.btnRePost.setOnClickListener {
            rePost(vh.textPoster.text.toString(), vh.absoluteAdapterPosition)
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
        val numRepost = posters[position].numRepost
        holder.tvNumComment.text =  "$numComments Comments"
        holder.tvNumRepost.text = "$numRepost Repost"
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