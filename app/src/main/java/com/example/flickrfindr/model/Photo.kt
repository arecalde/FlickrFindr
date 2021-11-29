package com.example.flickrfindr.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("owner")
    @Expose
    var owner: String? = null

    @SerializedName("secret")
    @Expose
    var secret: String? = null

    @SerializedName("server")
    @Expose
    var server: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("ispublic")
    @Expose
    var ispublic: Int? = null

    @SerializedName("isfriend")
    @Expose
    var isfriend: Int? = null

    @SerializedName("isfamily")
    @Expose
    var isfamily: Int? = null

    override fun toString(): String {
        return id ?: ""
    }
}