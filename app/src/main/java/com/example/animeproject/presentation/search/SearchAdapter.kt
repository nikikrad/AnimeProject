package com.example.animeproject.presentation.search

import android.os.Bundle
import android.util.Log
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
import com.example.animeproject.presentation.main.MainAdapter

class SearchAdapter(
    private val animeList: List<DataResponse>
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_anime_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(animeList[position])
    }

    override fun getItemCount(): Int = animeList.size

    class SearchViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.tv_NameAnime)
        private val coverImage: ImageView = itemView.findViewById((R.id.iv_Cover))
        private val posterImage: ImageView = itemView.findViewById((R.id.iv_Poster))

//        private val bundle = Bundle()

        fun bind(item: DataResponse) {
            val bundle = Bundle()
            name.text = item.attributes.titles.en_jp

            Glide.with(itemView)
                .load(item.attributes.posterImage.original)
                .placeholder(R.drawable.ic_search)
                .into(posterImage)

            try {
                Glide.with(itemView)
                    .load(item.attributes.coverImage.original)
                    .placeholder(R.drawable.ic_search)
                    .into(coverImage)
            }catch (e: Exception){
                Log.e("Error", e.localizedMessage)
            }



            itemView.setOnClickListener {
                bundle.putInt("ID", item.id)
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_searchFragment_to_fullAnimeInformationFragment, bundle)
            }
        }
    }
}