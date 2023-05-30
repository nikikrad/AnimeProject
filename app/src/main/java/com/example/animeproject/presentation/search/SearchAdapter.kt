package com.example.animeproject.presentation.search

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

class SearchAdapter(
    private val animeList: List<DataResponse>
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    companion object {
        var megastatus = false
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_mult_search, parent, false)
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
        private val posterImage: ImageView = itemView.findViewById((R.id.iv_Poster))
        private val ageRating: TextView = itemView.findViewById(R.id.tv_AgeRating)

        fun bind(item: DataResponse) {
            megastatus = false

            val bundle = Bundle()
            name.text = item.attributes.titles.en_jp

            Glide.with(itemView)
                .load(item.attributes.posterImage.original)
                .placeholder(R.drawable.ic_search)
                .into(posterImage)

           ageRating.text = item.attributes.ageRating

            itemView.setOnClickListener {
                if (megastatus == false) {
                    megastatus = true
                    bundle.putInt("ID", item.id)
                    Navigation.findNavController(itemView)
                        .navigate(
                            R.id.action_searchFragment_to_fullAnimeInformationFragment,
                            bundle
                        )
                }
            }
        }
    }
}