package com.example.animeproject.presentation.search.filter

import android.util.SparseBooleanArray
import androidx.core.util.forEach
import kotlin.properties.Delegates

object Filter {

    var all by Delegates.notNull<Boolean>()
    var comedy by Delegates.notNull<Boolean>()
    var adventure by Delegates.notNull<Boolean>()
    var isekai by Delegates.notNull<Boolean>()
    var school by Delegates.notNull<Boolean>()
    var magic by Delegates.notNull<Boolean>()
    var horror by Delegates.notNull<Boolean>()
    var music by Delegates.notNull<Boolean>()

    private val genreList = mutableListOf<Boolean>()

    fun changeGenre(id: Int){

        when(id){
            0->{
                all=true
                comedy=false
                adventure=false
                isekai=false
                school=false
                magic=false
                horror=false
                music=false
                genreList.clear()
                genreList.add(all)
                genreList.add(comedy)
                genreList.add(adventure)
                genreList.add(isekai)
                genreList.add(school)
                genreList.add(magic)
                genreList.add(horror)
                genreList.add(music)
            }
            1->{
                all=false
                comedy=true
                adventure=false
                isekai=false
                school=false
                magic=false
                horror=false
                music=false
                genreList.clear()
                genreList.add(all)
                genreList.add(comedy)
                genreList.add(adventure)
                genreList.add(isekai)
                genreList.add(school)
                genreList.add(magic)
                genreList.add(horror)
                genreList.add(music)
            }
            2->{
                all=false
                comedy=false
                adventure=true
                isekai=false
                school=false
                magic=false
                horror=false
                music=false
                genreList.clear()
                genreList.add(all)
                genreList.add(comedy)
                genreList.add(adventure)
                genreList.add(isekai)
                genreList.add(school)
                genreList.add(magic)
                genreList.add(horror)
                genreList.add(music)
            }
            3->{
                all=false
                comedy=false
                adventure=false
                isekai=true
                school=false
                magic=false
                horror=false
                music=false
                genreList.clear()
                genreList.add(all)
                genreList.add(comedy)
                genreList.add(adventure)
                genreList.add(isekai)
                genreList.add(school)
                genreList.add(magic)
                genreList.add(horror)
                genreList.add(music)
            }
            4->{
                all=false
                comedy=false
                adventure=false
                isekai=false
                school=true
                magic=false
                horror=false
                music=false
                genreList.clear()
                genreList.add(all)
                genreList.add(comedy)
                genreList.add(adventure)
                genreList.add(isekai)
                genreList.add(school)
                genreList.add(magic)
                genreList.add(horror)
                genreList.add(music)
            }
            5->{
                all=false
                comedy=false
                adventure=false
                isekai=false
                school=false
                magic=true
                horror=false
                music=false
                genreList.clear()
                genreList.add(all)
                genreList.add(comedy)
                genreList.add(adventure)
                genreList.add(isekai)
                genreList.add(school)
                genreList.add(magic)
                genreList.add(horror)
                genreList.add(music)
            }
            6->{
                all=false
                comedy=false
                adventure=false
                isekai=false
                school=false
                magic=false
                horror=true
                music=false
                genreList.clear()
                genreList.add(all)
                genreList.add(comedy)
                genreList.add(adventure)
                genreList.add(isekai)
                genreList.add(school)
                genreList.add(magic)
                genreList.add(horror)
                genreList.add(music)
            }
            7->{
                all=false
                comedy=false
                adventure=false
                isekai=false
                school=false
                magic=false
                horror=false
                music=true
                genreList.clear()
                genreList.add(all)
                genreList.add(comedy)
                genreList.add(adventure)
                genreList.add(isekai)
                genreList.add(school)
                genreList.add(magic)
                genreList.add(horror)
                genreList.add(music)
            }
        }
    }


    fun getActiveGenre(): Int{
        var kostil = 0
        genreList.forEach {
            if (it) return kostil
            kostil++
        }
        return 0
    }
    fun getActiveGenreLikeText(): String{
        var kostil = 0
        genreList.forEach {
            if (it){
                when(kostil){
                    0 -> {
                        return "all"
                    }
                    1 -> {
                        return "comedy"
                    }
                    2 -> {
                        return "adventure"
                    }
                    3 -> {
                        return "isekai"
                    }
                    4 -> {
                        return "school"
                    }
                    5 -> {
                        return "magic"
                    }
                    6 -> {
                        return "horror"
                    }
                    7 -> {
                        return "music"
                    }
                }
            }
            kostil++
        }
        return "all"
    }
}