package com.example.flickrfindr.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrfindr.R
import com.example.flickrfindr.databinding.HomeFragmentBinding
import com.example.flickrfindr.databinding.PhotoItemBinding
import com.example.flickrfindr.model.Photo
import com.squareup.picasso.Picasso

class Home : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = HomeFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val recyclerView = binding.photoRecyclerView

        val observedItems = mutableListOf<String>()
        viewModel.photos.observe(viewLifecycleOwner) { map ->

            recyclerView.adapter = PhotoItemAdapter(map.toList().map { it.second }, viewLifecycleOwner)

            map.forEach { entry ->
                if (observedItems.contains(entry.key)) return@forEach

                val photoItem = entry.value
                photoItem.goToImage.observeEvent(viewLifecycleOwner) {
                    Toast.makeText(requireContext(), "Show Image Page", Toast.LENGTH_LONG).show()
                    //findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToProfileFragment(postItem.owner, postItem.username, postItem.avatar))
                }
                observedItems.add(entry.key)
            }
        }

        return binding.root
    }
}

class MyViewHolder(private val binding: PhotoItemBinding, private val lifecycleOwner: LifecycleOwner) : RecyclerView.ViewHolder(binding.getRoot()) {

    fun bind(item: Photo) {
        binding.lifecycleOwner = lifecycleOwner
        binding.photo = item
        binding.executePendingBindings()
    }
}

class PhotoItemAdapter(private val items: List<Photo>, private val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemBinding = DataBindingUtil.inflate<PhotoItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.photo_item,
            parent,
            false
        )
        return MyViewHolder(itemBinding, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item: Photo = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size
}