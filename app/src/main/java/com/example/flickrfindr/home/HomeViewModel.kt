package com.example.flickrfindr.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.flickrfindr.model.Photo
import com.example.flickrfindr.network.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val _photos = MutableLiveData(mapOf<String, Photo>())
    val photos: LiveData<Map<String, Photo>> = _photos

    init {
        val apiService = APIService.retrofit.create(APIService::class.java)
        val call = apiService.getImages(mapOf(
            "api_key" to "1508443e49213ff84d566777dc211f2a",
            "text" to "dogs",
            "method" to "flickr.photos.search",
            "format" to "json",
            "nojsoncallback" to "1",
            "extras" to "url_c,url_t",
            "per_page" to "25"
        ))

        viewModelScope.launch(Dispatchers.IO) {
            val result = call.execute()
            val photosFromCall = result.body()?.photos?.photos ?: return@launch
            val photoMap = mutableMapOf<String, Photo>()

            photosFromCall.forEach {
                val key = it.id ?: return@forEach
                photoMap[key] = it
            }

            _photos.postValue(photoMap)
        }
    }
}