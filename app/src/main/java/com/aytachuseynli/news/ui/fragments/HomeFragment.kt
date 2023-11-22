package com.aytachuseynli.news.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aytachuseynli.news.R
import com.aytachuseynli.news.data.entity.Article
import com.aytachuseynli.news.databinding.FragmentHomeBinding
import com.aytachuseynli.news.ui.adapter.NewsAdapter
import com.aytachuseynli.news.ui.viewmodel.HomeViewModel
import com.aytachuseynli.news.util.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val newsAdapter by lazy {
        NewsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        binding.todayDate.text = formattedDate

        viewModel.fetchTopHeadlines()

        // Navigate to the LanguageFragment
        binding.langBtn.setOnClickListener {
            findNavController().safeNavigate(HomeFragmentDirections.actionHomeFragmentToLanguageFragment())
        }

        // Navigate to the DetailFragment
        newsAdapter.onItemClick = {
            findNavController().safeNavigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
        }

    }

    private fun setupRecyclerView() {
        binding.rvNews.adapter = newsAdapter
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.articleList.flowWithLifecycle(lifecycle).collect { articleList ->
                newsAdapter.articleList = articleList
            }
            viewModel.searchList.flowWithLifecycle(lifecycle).collect { searchList ->
                newsAdapter.articleList = searchList
            }
        }
    }
}