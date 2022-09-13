package com.example.animeproject.presentation.main

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

class MainAdapter(
    private val animeList: List<DataResponse>
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_anime, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(animeList[position])
    }

    override fun getItemCount(): Int = animeList.size

    class MainViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.tv_Title)
        private val avatar: ImageView = itemView.findViewById((R.id.iv_Image))
//        private val bundle = Bundle()

        fun bind(item: DataResponse) {
            name.text = item.attributes.titles.en_jp

            Glide.with(itemView)
                .load(item.attributes.posterImage.original)
                .placeholder(R.drawable.ic_search)
                .into(avatar)


//            itemView.setOnClickListener {
//                bundle.putString("ID", item.id)
//                Navigation.findNavController(itemView)
//                    .navigate(R.id.action_mainFragment_to_descriptionCoinFragment, bundle)
//            }
        }
    }
}