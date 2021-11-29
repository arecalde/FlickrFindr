package com.example.flickrfindr.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PhotoWrapper {
    @SerializedName("photos")
    @Expose
    var photos: ImageResult? = null

    @SerializedName("stat")
    @Expose
    var stat: String? = null

    override fun toString(): String {
        return "$photos $stat"
    }
}