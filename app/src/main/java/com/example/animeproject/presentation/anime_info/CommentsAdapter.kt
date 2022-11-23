package com.example.animeproject.presentation.anime_info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animeproject.R
import com.example.animeproject.presentation.anime_info.model_request.Comments

class CommentsAdapter(
    private val movieList: MutableList<Comments>
) : RecyclerView.Adapter<CommentsAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_comments, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    class MainViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.tv_UserName)
        private val comment: TextView = itemView.findViewById(R.id.tv_Comment)
        fun bind(item: Comments) {
            name.text = item.user_name.substringBefore("@")
            comment.text = item.comment
        }
    }
}