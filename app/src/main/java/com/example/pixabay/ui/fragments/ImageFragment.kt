package com.example.pixabay.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pixabay.R
import com.example.pixabay.databinding.FragmentImageBinding
import com.example.pixabay.ui.adapter.PixabayAdapter
import com.example.pixabay.ui.viewmodels.PixabayViewModel
import com.example.pixabay.utils.Resource
import com.example.pixabay.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ImageFragment : Fragment(R.layout.fragment_image) {
    private lateinit var binding: FragmentImageBinding
    private val viewModel: PixabayViewModel by viewModels()
    private lateinit var pixabayAdapter: PixabayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        pixabayAdapter = PixabayAdapter(PixabayAdapter.OnClickListener { photo ->
            val action = ImageFragmentDirections.actionImageFragmentToDetailFragment(photo)
            findNavController().navigate(action)

        })

        viewModel.searchQuery.value?.let { subscribeOnline(it) }

        binding.searchLayout.setEndIconOnClickListener {
            subscribeOnline(binding.searchLayout.editText?.text.toString())
            binding.progressBar.isVisible = true
            hideKeyboard()
        }
        return binding.root
    }

    private fun subscribeOnline(query: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getImages(query).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        binding.progressBar.isVisible = false
                        binding.imageRecycler.isVisible = true
                        if (result.data?.isEmpty()!!) {
                            Toast.makeText(requireContext(), "No Images", Toast.LENGTH_LONG).show()
                        } else {
                            val pix = result.data
                            pixabayAdapter.submitList(pix)
                            binding.imageRecycler.adapter = pixabayAdapter
                            Timber.d("${result.data}")
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.isVisible = true

                    }
                    is Resource.Loading -> {
                        binding.progressBar.isVisible = true

                    }
                }

            }
        }
    }
}

