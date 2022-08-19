package com.farhanrv.worldnews.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhanrv.worldnews.databinding.ItemTrendingCountryBinding
import com.farhanrv.worldnews.domain.model.News

class CountryNewsAdapter : RecyclerView.Adapter<CountryNewsAdapter.ViewHolder>() {

    private var listData = ArrayList<News>()
    var onItemClick: ((News) -> Unit)? = null

    fun setData(newListData: List<News>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemTrendingCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentData = listData[position]
        holder.bind(currentData)
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(private val binding: ItemTrendingCountryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: News) {
            binding.tvTitle.text = data.title
            binding.tvDescription.text = data.description
            binding.tvDate.text = "1 hrs ago" // data.publishDate
            Glide.with(itemView.context)
                .load(data.image)
                .into(binding.imgThumbnail)
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[absoluteAdapterPosition])
            }
        }
    }
}