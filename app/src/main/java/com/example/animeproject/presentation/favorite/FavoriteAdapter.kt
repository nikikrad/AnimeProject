package com.example.animeproject.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeproject.R
import com.example.animeproject.domain.response.DataResponse
import com.example.animeproject.presentation.anime_info.model_request.AnimeRequest

class FavoriteAdapter (
    private val animeList: MutableList<AnimeRequest>
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(animeList[position])
    }

    override fun getItemCount(): Int = animeList.size

    class FavoriteViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.tv_Title)
        private val avatar: ImageView = itemView.findViewById((R.id.iv_Poster))
        private val description: TextView = itemView.findViewById(R.id.tv_Description)

        fun bind(item: AnimeRequest) {
            name.text = item.title
            description.text = item.description

            Glide.with(itemView)
                .load(item.poster)
                .placeholder(R.drawable.ic_search)
                .into(avatar)


        }
    }
}