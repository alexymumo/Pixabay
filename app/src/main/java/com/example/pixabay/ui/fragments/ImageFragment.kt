package com.example.pixabay.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pixabay.R
import com.example.pixabay.databinding.FragmentImageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFragment : Fragment() {
    private lateinit var binding: FragmentImageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageBinding.bind(view)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding = FragmentImageBinding.bind()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }
}