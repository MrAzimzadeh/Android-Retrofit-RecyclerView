package com.azimzada.retrofitrecycleexamson.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.azimzada.retrofitrecycleexamson.R
import com.azimzada.retrofitrecycleexamson.model.Comment

class CommentAdapter(var list : List<Comment>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.nameText)
        var email = itemView.findViewById<TextView>(R.id.emailText)
        var comment = itemView.findViewById<TextView>(R.id.commnetText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comment_row,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(list.get(position).name)
        holder.email.setText(list.get(position).email)
        holder.comment.setText(list.get(position).body)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}