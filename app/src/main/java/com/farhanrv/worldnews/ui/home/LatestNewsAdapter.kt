package com.farhanrv.worldnews.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhanrv.worldnews.data.source.local.entity.NewsEntity
import com.farhanrv.worldnews.databinding.ItemLatestNewsBinding
import com.farhanrv.worldnews.domain.model.News

class LatestNewsAdapter : RecyclerView.Adapter<LatestNewsAdapter.ViewHolder>() {

    private var listData = ArrayList<News>()
    var onItemClick: ((News) -> Unit)? = null

    fun setData(newListData: List<News>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestNewsAdapter.ViewHolder =
        ViewHolder(ItemLatestNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: LatestNewsAdapter.ViewHolder, position: Int) {
        val currentData = listData[position]
        holder.bind(currentData)
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(private val binding: ItemLatestNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: News) {
            binding.tvTitle.text = data.title
            binding.tvDate.text = data.publishDate
            Glide.with(itemView.context)
                .load(data.image)
                .into(binding.image)

        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[absoluteAdapterPosition])
            }
        }
    }
}