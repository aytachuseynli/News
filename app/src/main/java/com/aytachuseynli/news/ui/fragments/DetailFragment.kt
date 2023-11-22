package com.aytachuseynli.news.ui.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aytachuseynli.news.R
import com.aytachuseynli.news.databinding.FragmentDetailBinding
import com.aytachuseynli.news.ui.viewmodel.SavedViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val savedViewModel: SavedViewModel by viewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedArticle = args.article
        binding.article = selectedArticle
        binding.newsTitle.text = selectedArticle.title

        Glide.with(binding.root)
            .load(selectedArticle.urlToImage)
            .into(binding.detailImage)

        Log.e("args", " ${args.article} ")
        binding.detailBackBtn.setOnClickListener {
            // Navigate back to HomeFragment when the button is clicked
            findNavController().popBackStack()
        }

        // Popup menu
        binding.popUpBtn.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), binding.popUpBtn)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_save -> {

                        val selectedArticle = args.article
//                        lifecycleScope.launch {
//                            val isSaved = savedViewModel.isArticleSaved(selectedArticle)
//
//                            if (!isSaved) {
//                                savedViewModel.saveArticle(selectedArticle)
//                            } else {
//
//                            }
//                        }


                        // Log to verify that the selected article is being saved
                        Log.d(TAG, "Saving article: $selectedArticle")

                        lifecycleScope.launch {
                            savedViewModel.saveArticle(selectedArticle)
                        }


                        true
                    }

                    R.id.action_share -> {
                        true
                    }

                    else -> false
                }
            }
            popupMenu.show()
        }



        binding.detailTextView.setOnClickListener {
            val webPageUrl = selectedArticle.url
            if (!webPageUrl.isNullOrBlank()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webPageUrl))
                startActivity(intent)
            }
        }


    }

    }



