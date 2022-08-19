package com.farhanrv.worldnews.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhanrv.worldnews.BuildConfig
import com.farhanrv.worldnews.data.source.AppRepository

class HomeViewModel(private val repository: AppRepository) : ViewModel() {

    val query: MutableLiveData<String> = MutableLiveData()
    val category: MutableLiveData<String> = MutableLiveData()

    fun newsByQuery() = repository.getNews(query.value?: "a", BuildConfig.API_KEY, "publishedAt").asLiveData()

    fun countryNews() = repository.getCountryNews(category.value,"id", BuildConfig.API_KEY).asLiveData()
}