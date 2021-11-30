package com.example.flickrfindr.home

import android.app.Application
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.flickrfindr.extensions.Event
import com.example.flickrfindr.model.Photo
import com.example.flickrfindr.network.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _photos = MutableLiveData(mapOf<String, Photo>())
    val photos: LiveData<Map<String, Photo>> = _photos

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    val hideKeyboard = Event(Unit)

    val onQueryTextListener = object: SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            callImages(query)
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    }

    fun callImages(query: String?) {
        if (query.isNullOrEmpty()) return
        val apiService = APIService.retrofit.create(APIService::class.java)
        val call = apiService.getImages(mapOf(
            "api_key" to "1508443e49213ff84d566777dc211f2a",
            "text" to query,
            "method" to "flickr.photos.search",
            "format" to "json",
            "nojsoncallback" to "1",
            "extras" to "url_t,url_o",
            "per_page" to "25"
        ))

        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = call.execute()
            val photosFromCall = result.body()?.photos?.photos ?: return@launch
            val photoMap = mutableMapOf<String, Photo>()

            photosFromCall.forEach {
                val key = it.id ?: return@forEach
                photoMap[key] = it
            }

            _photos.postValue(photoMap)
            _loading.postValue(false)
            viewModelScope.launch(Dispatchers.Main) { hideKeyboard.raiseEvent(Unit) }
        }
    }
}

@BindingAdapter("queryTextListener")
fun setOnQueryTextListener(searchView: SearchView, listener: SearchView.OnQueryTextListener) {
    searchView.setOnQueryTextListener(listener);
}