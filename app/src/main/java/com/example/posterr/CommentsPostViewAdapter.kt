package com.example.posterr

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.posterr.placeholder.PlaceholderContent.PlaceholderItem
import com.example.posterr.databinding.FragmentListCommentsPostBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class CommentsPostViewAdapter(
    private val itemPost: List<String>
) : RecyclerView.Adapter<CommentsPostViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentListCommentsPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idView.text = itemPost[position]
    }

    override fun getItemCount(): Int = itemPost.size

    inner class ViewHolder(binding: FragmentListCommentsPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
    }

}