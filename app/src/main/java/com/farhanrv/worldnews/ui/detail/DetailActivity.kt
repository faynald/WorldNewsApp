package com.farhanrv.worldnews.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.farhanrv.worldnews.R
import com.farhanrv.worldnews.databinding.ActivityDetailBinding
import com.farhanrv.worldnews.domain.model.News

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intentData = intent.getParcelableExtra<News>(EXTRA_DATA)
        
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

        showDetail(intentData!!)

    }

    private fun showDetail(data: News) {
        with(binding) {
            tvTitle.text = data.title
            tvDate.text = data.publishDate
            tvContent.text = data.content
            Glide.with(this@DetailActivity)
                .load(data.image)
                .into(imgThumbnail)
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}