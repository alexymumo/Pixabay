package com.example.pixabay.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pixabay.R
import com.example.pixabay.databinding.FragmentImageBinding
import com.example.pixabay.ui.adapter.PixabayAdapter
import com.example.pixabay.ui.viewmodels.PixabayViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFragment : Fragment(R.layout.fragment_image) {
    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PixabayViewModel by viewModels()
    private lateinit var pixabayAdapter: PixabayAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpUI()
        setUpOnClickListener()
    }


    private fun setUpUI() {
    }


    private fun setUpRecyclerView() {
        pixabayAdapter = PixabayAdapter()
        binding.imageRecycler.apply {
            adapter = pixabayAdapter
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
        }
    }
    private fun setUpOnClickListener() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}