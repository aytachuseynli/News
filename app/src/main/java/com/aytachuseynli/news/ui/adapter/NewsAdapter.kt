package com.aytachuseynli.news.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aytachuseynli.news.data.entity.Article
import com.aytachuseynli.news.databinding.ItemNewsBinding
import com.bumptech.glide.Glide


class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var onItemClick: (Article) -> Unit={}
    var articleList: List<Article> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int = articleList.size

    inner class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article

            Glide.with(binding.root)
                .load(article.urlToImage)
                .into(binding.newsImage)

            binding.newsCard.setOnClickListener { onItemClick(article) }
            binding.executePendingBindings()
        }
    }
}