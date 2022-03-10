package com.example.pixabay.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pixabay.R
import com.example.pixabay.databinding.FragmentImageBinding
import com.example.pixabay.ui.adapter.PixabayAdapter
import com.example.pixabay.ui.viewmodels.PixabayViewModel
import com.example.pixabay.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setSearchView()
        setUpOnClickListener()
        setUpUi()
    }

    private fun setUpUi() {
        viewModel.images.observe(viewLifecycleOwner) { result ->
            binding.apply {

            }
        }
    }

    private fun setUpOnClickListener() {
        findNavController().navigate(R.id.action_imageFragment_to_detailFragment)
    }


    private fun setUpRecyclerView() {
        pixabayAdapter = PixabayAdapter()
        binding.imageRecycler.apply {
            adapter = pixabayAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
    private fun setSearchView() {

    }



    private fun searchImages(query: String) {
        viewModel.searched.observe(viewLifecycleOwner, { result ->
            when(result) {
                is Resource.Success -> {
                    hideProgressBar()
                    binding.imageRecycler.isVisible = true
                    result.data.let {  searchResponse ->
                        pixabayAdapter.submitList(searchResponse?.hits)
                        viewModel.getImages(query)
                        binding.imageRecycler.adapter = pixabayAdapter
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }

                is Resource.Error -> {
                    hideProgressBar()
                    showToast()
                }

            }

        })
    }

    private fun showToast() {
        Toast.makeText(requireContext(), "An error occurred", Toast.LENGTH_LONG).show()
    }

    /*private fun setUpRecyclerView() {
        pixabayAdapter = PixabayAdapter()
        binding.imageRecycler.apply {
            adapter = pixabayAdapter
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
        }
    }

     */
    private fun hideProgressBar() {
        binding.progressBar.isInvisible = false
    }

    private fun showProgressBar() {
        binding.progressBar.isVisible = true

    }

}