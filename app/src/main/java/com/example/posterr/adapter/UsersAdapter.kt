package com.example.posterr.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.posterr.R
import com.example.posterr.models.User

class UsersAdapter(
    private val users: MutableList<User>
): RecyclerView.Adapter<UsersAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        val vh = VH(v)
        vh.itemView.setOnClickListener {
            Log.d("TESTING", "Poster clicked")
        }
        return vh
    }

    override fun onBindViewHolder(holder: UsersAdapter.VH, position: Int) {
        val text = users[position].username
        holder.textUser.text = text
    }

    override fun getItemCount(): Int = users.size
    inner class VH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textUser: TextView = itemView.findViewById(R.id.tvUser)
    }
}