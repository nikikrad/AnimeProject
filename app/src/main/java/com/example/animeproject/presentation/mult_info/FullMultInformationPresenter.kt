package com.example.animeproject.presentation.mult_info

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentManager
import com.example.animeproject.databinding.FragmentFullMultInformationBinding
import com.example.animeproject.presentation.mult_info.model_request.Comments
import com.example.animeproject.presentation.mult_info.repository.FullAnimeInformationRepository
import com.example.animeproject.presentation.dialog_description.DescriptionDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import moxy.MvpPresenter
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import javax.inject.Inject

class FullMultInformationPresenter(
    private val fullMultInformationRepository: FullAnimeInformationRepository,
    private val fullMultInformationView: FullMultInformationView
) : MvpPresenter<FullMultInformationView>() {

    @Inject
    lateinit var database: DatabaseReference
    @Inject
    lateinit var auth: FirebaseAuth

    private val disposable = CompositeDisposable()

    fun getMultById(
        id: Int,
        binding: FragmentFullMultInformationBinding,
        dialog: DescriptionDialogFragment,
        fragmentManager: FragmentManager
    ) {
        disposable.add(
            fullMultInformationRepository.getApiService().getMultById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    fullMultInformationView.getMultById(it, binding, dialog, fragmentManager)
                }, {
                    Log.e("Error", it.localizedMessage)
                })
        )
    }
    private var megaStatus = true

    fun getStatusMult(id:String):Observable<Boolean> {
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        return Observable.create { observable ->
            database.child(auth.currentUser?.email.toString().substringBefore("@")).get()
                .addOnSuccessListener {
                    it.children.forEach { data ->
                        if (data.key.toString() == id) {
                            megaStatus = false
                        }
                    }
                    observable.onNext(megaStatus)
                }
        }

    }

    fun sendComment(commentId: String, id: String, date: String, comment:String){
        var auth = FirebaseAuth.getInstance()
        var database = Firebase.database.reference
        GlobalScope.launch(Dispatchers.IO){
            database.child("Comments").child(id).child(commentId).setValue(Comments(commentId, id, auth.currentUser?.email.toString(), comment, date))
        }
    }

    fun processingData(id: String): Observable<MutableList<Comments>> {
        var database = Firebase.database.reference
        var tempCommList: MutableList<Comments> = emptyList<Comments>().toMutableList()
        return  Observable.create { observable ->
            database.child("Comments").child(id)
                .get()
                .addOnSuccessListener {movieId->
                    movieId.children.forEach { comments ->
                        tempCommList.add(Comments(
                            comments.child("comment_id").value.toString(),
                            comments.child("movie_id").value.toString(),
                            comments.child("user_name").value.toString(),
                            comments.child("comment").value.toString(),
                            comments.child("date").value.toString()
                        ))
                    }
                    observable.onNext(tempCommList)
                }
        }

    }

    fun bitmapImage(context: Context, imageUrl: String): Uri? {
        val bmp: Bitmap = BitmapFactory.decodeStream(URL(imageUrl).openStream())
        val cachePath = File(context.cacheDir, "images")
        cachePath.mkdirs()
        val imageFile = File(cachePath, "shared_image.jpg")
        val stream = FileOutputStream(imageFile)
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        stream.close()
        return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", imageFile)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

}

