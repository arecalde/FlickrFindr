package com.example.flickrfindr.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.flickrfindr.R
import com.example.flickrfindr.databinding.PhotoFragmentBinding

class Photo : Fragment() {
    private val args: PhotoArgs by navArgs()

    private val viewModel: PhotoViewModel by viewModels {
        PhotoViewModelFactory(
            requireActivity().application,
            args.url
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = PhotoFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}