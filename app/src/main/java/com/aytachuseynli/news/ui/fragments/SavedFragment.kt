package com.aytachuseynli.news.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.aytachuseynli.news.R
import com.aytachuseynli.news.databinding.FragmentSavedBinding
import com.aytachuseynli.news.ui.adapter.ArticleAdapter
import com.aytachuseynli.news.ui.viewmodel.SavedViewModel
import com.aytachuseynli.news.ui.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class SavedFragment : Fragment() {

    private lateinit var binding: FragmentSavedBinding
//    private val sharedViewModel: SharedViewModel by viewModels()
    private val savedViewModel: SavedViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        savedViewModel.savedArticles.observe(viewLifecycleOwner, { savedArticles ->
            Toast.makeText(requireContext(), "savedArticles $savedArticles", Toast.LENGTH_SHORT).show()
            val adapter = ArticleAdapter(savedArticles) { clickedArticle ->
                // Handle item click here, e.g., navigate to detail fragment
            }
            binding.rvSaved.adapter = adapter
        })

        val currentDate = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        binding.todayDate.text = formattedDate


    }
}