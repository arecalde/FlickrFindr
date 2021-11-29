package com.example.flickrfindr.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.flickrfindr.network.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    var text = MutableLiveData("Hello World")

    init {
        val apiService = APIService.retrofit.create(APIService::class.java)
        val call = apiService.getImages(mapOf(
            "api_key" to "1508443e49213ff84d566777dc211f2a",
            "text" to "dogs",
            "method" to "flickr.photos.search",
            "format" to "json",
            "nojsoncallback" to "1"
        ))

        viewModelScope.launch(Dispatchers.IO) {
            val result = call.execute()
            Log.i("Result", result.body().toString())
        }
    }
}