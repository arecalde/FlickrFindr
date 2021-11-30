package com.example.flickrfindr.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("url_t")
    @Expose
    var url_t: String? = null

    @SerializedName("url_c")
    @Expose
    var url_c: String? = null

    override fun toString(): String {
        return "$id\n$url_c\n$url_c"
    }
}