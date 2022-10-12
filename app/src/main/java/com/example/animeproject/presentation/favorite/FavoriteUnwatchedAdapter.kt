package com.example.animeproject.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeproject.R
import com.example.animeproject.presentation.anime_info.model_request.AnimeRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FavoriteUnwatchedAdapter(
    private val animeList: MutableList<AnimeRequest>
) : RecyclerView.Adapter<FavoriteUnwatchedAdapter.UnWatchedViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UnWatchedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_favorite, parent, false)
        return UnWatchedViewHolder(view)
    }

    override fun onBindViewHolder(holder: UnWatchedViewHolder, position: Int) {
        holder.bind(animeList[position])
        holder.constraint.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.alpha_anim)
    }

    override fun getItemCount(): Int = animeList.size

    class UnWatchedViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {


        val database: DatabaseReference = Firebase.database.reference
        val auth: FirebaseAuth = FirebaseAuth.getInstance()

        private val name: TextView = itemView.findViewById(R.id.tv_Title)
        private val avatar: ImageView = itemView.findViewById((R.id.iv_Poster))
//        private val description: TextView = itemView.findViewById(R.id.tv_Description)
        private val rating: TextView = itemView.findViewById(R.id.tv_Rating)
        val constraint: ConstraintLayout = itemView.findViewById(R.id.constraintMain)
        private val watcher: ToggleButton = itemView.findViewById(R.id.btn_Watching)

        private var bundle = Bundle()

        fun bind(item: AnimeRequest) {
            name.text = item.title
//            description.text = item.description
            rating.text = "Рейтинг - ${item.rating}"

            Glide.with(itemView)
                .load(item.poster)
                .placeholder(R.drawable.ic_search)
                .into(avatar)

            watcher.isChecked = item.status

            watcher.setOnClickListener {
                if (item.status){
                    database.child(
                        auth.currentUser?.email.toString().substringBefore("@")
                    )
                        .child(item.id.toString()).child("status").setValue(false)
                    item.status = false
                    watcher.isChecked = item.status
                }else{
                    database.child(
                        auth.currentUser?.email.toString().substringBefore("@")
                    )
                        .child(item.id.toString()).child("status").setValue(true)
                    item.status = true
                    watcher.isChecked = item.status
                }
            }

            itemView.setOnClickListener {
                bundle.putInt("ID", item.id!!.toInt())
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_favoriteFragment_to_fullAnimeInformationFragment, bundle)
            }

        }
    }
}