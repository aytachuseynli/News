package com.aytachuseynli.news.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aytachuseynli.news.R
import com.aytachuseynli.news.databinding.FragmentLanguageBinding
import com.aytachuseynli.news.ui.viewmodel.LanguageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageFragment : Fragment() {
    private lateinit var binding: FragmentLanguageBinding
    private val viewModel: LanguageViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_language, container, false)

        binding.langBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root


    }


}