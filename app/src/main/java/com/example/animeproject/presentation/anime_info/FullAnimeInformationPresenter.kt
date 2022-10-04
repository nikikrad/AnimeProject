package com.example.animeproject.presentation.anime_info

import android.util.Log
import androidx.fragment.app.FragmentManager
import com.example.animeproject.databinding.FragmentFullAnimeInformationBinding
import com.example.animeproject.domain.response.AnimeResponse
import com.example.animeproject.domain.response.DataResponse
import com.example.animeproject.domain.response.SingleAnimeResponse
import com.example.animeproject.presentation.anime_info.repository.FullAnimeInformationRepository
import com.example.animeproject.presentation.dialog_description.DescriptionDialogFragment
import com.example.animeproject.presentation.main.MainView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.delay
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

class FullAnimeInformationPresenter(
    private val fullAnimeInformationRepository: FullAnimeInformationRepository,
    private val fullAnimeInformationView: FullAnimeInformationView
) : MvpPresenter<FullAnimeInformationView>() {


    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private val disposable = CompositeDisposable()

    fun getAnimeById(
        id: Int,
        binding: FragmentFullAnimeInformationBinding,
        dialog: DescriptionDialogFragment,
        fragmentManager: FragmentManager
    ) {
        disposable.add(
            fullAnimeInformationRepository.getApiService().getAnimeById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    fullAnimeInformationView.getAnimeById(it, binding, dialog, fragmentManager)
                }, {
                    Log.e("Error", it.localizedMessage)
                })
        )
    }
    private var megaStatus = true

    fun getStatusAnime(id:String):Observable<Boolean>{
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        return Observable.create{ observable ->
            database.child(auth.currentUser?.email.toString().substringBefore("@")).get()
                .addOnSuccessListener {
                    it.children.forEach { data ->
                        if (data.key.toString() == id) {
                            megaStatus = false
                        }
                    }
                }
            Thread.sleep(1000)
            observable.onNext(megaStatus)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

}

