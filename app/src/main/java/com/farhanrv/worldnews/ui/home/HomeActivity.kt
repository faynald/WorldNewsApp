package com.farhanrv.worldnews.ui.home

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_DATA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.farhanrv.worldnews.R
import com.farhanrv.worldnews.data.source.AppResource
import com.farhanrv.worldnews.databinding.ActivityHomeBinding
import com.farhanrv.worldnews.ui.detail.DetailActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val latestNewsAdapter = LatestNewsAdapter()
        val countryNewsAdapter = CountryNewsAdapter()

        latestNewsAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@HomeActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }
        countryNewsAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@HomeActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        var temp: Chip = binding.homeContent.chipAll

        binding.homeContent.chipGroup.setOnCheckedStateChangeListener { group, _ ->
            val id = resources.getResourceEntryName(group.checkedChipId)
            val category = id.split("_").drop(1).take(1)[0]

            temp.isChipIconVisible = true

            val viewTest = findViewById<Chip>(group.checkedChipId)
            temp = viewTest
            viewTest.isChipIconVisible = false


            if (category == "all") {
                viewModel.query.postValue("a")
                viewModel.category.postValue(null)
            } else {
                viewModel.query.postValue(category)
                viewModel.category.postValue(category)
            }

            // TODO : chip selected is all
            // latestNews = everything
            // trending country = top-headline

            // TODO : chip selected is business
            // latestNews = without country (category, key)
            // top-headline without country

            // trending country = with country=id (country, category, key)
            // top-headline with country
        }

        viewModel.newsByQuery().observe(this) { newsData ->
            if (newsData != null) {
                when (newsData) {
                    is AppResource.Loading -> Log.e("observe news", "Loading . . .")
                    is AppResource.Success -> latestNewsAdapter.setData(newsData.data?.filter { it.title != null })
                    is AppResource.Error -> Log.e("observe news", "Error")
                }
            }
        }

        viewModel.countryNews().observe(this) { newsData ->
            if (newsData != null) {
                when (newsData) {
                    is AppResource.Loading -> Log.e("observe countryNews", "Loading . . .")
                    is AppResource.Success -> countryNewsAdapter.setData(newsData.data?.filter { it.title != null })
                    is AppResource.Error -> Log.e("observe countryNews", "Error")
                }
            }
        }

//        binding.homeContent.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.homeContent.rvLatestNews.setHasFixedSize(true)
        binding.homeContent.rvLatestNews.adapter = latestNewsAdapter

        binding.homeContent.rvCountryNews.setHasFixedSize(true)
        binding.homeContent.rvCountryNews.adapter = countryNewsAdapter


    }
}