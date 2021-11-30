package com.example.flickrfindr.home

import android.app.Application
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class PhotoViewModel(application: Application, val url: String) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel
}

class PhotoViewModelFactory(private val application: Application, private val url: String):
ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel(application, url) as T
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view : View, url : String?){
    if (url.isNullOrEmpty()) return
    Picasso.get().load(url).into((view as ImageView))
}