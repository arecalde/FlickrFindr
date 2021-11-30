package com.example.flickrfindr.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.flickrfindr.extensions.Event
import com.squareup.picasso.Picasso

class Photo {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("url_t")
    @Expose
    var url_t: String? = null

    @SerializedName("url_o")
    @Expose
    var url_o: String? = null

    val goToImage = Event(Unit)

    fun goToImageFragment() {
        goToImage.raiseEvent(Unit)
    }
    override fun toString(): String {
        return "$id\n$url_t\n$url_o"
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view : View, url : String?){
    if (url.isNullOrEmpty()) return
    Picasso.get().load(url).into((view as ImageView))
}