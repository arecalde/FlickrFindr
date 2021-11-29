package com.example.flickrfindr.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ImageResult {
    @SerializedName("page")
    @Expose
    var page: Int? = null

    @SerializedName("pages")
    @Expose
    var pages: Int? = null

    @SerializedName("perpage")
    @Expose
    var perpage: Int? = null

    @SerializedName("total")
    @Expose
    var total: Int? = null

    @SerializedName("photo")
    @Expose
    var photos: List<Photo>? = null

    override fun toString(): String {
        return "page: $page\npages: $pages\nperpage: $perpage\ntotal: $total\n\nphotos:\n$photos"
    }
}

