package com.farhanrv.worldnews.di

import androidx.room.Room
import com.farhanrv.worldnews.data.source.AppExecutors
import com.farhanrv.worldnews.data.source.AppRepository
import com.farhanrv.worldnews.data.source.local.LocalDataSource
import com.farhanrv.worldnews.data.source.local.room.AppDatabase
import com.farhanrv.worldnews.data.source.remote.RemoteDataSrouce
import com.farhanrv.worldnews.data.source.remote.network.ApiService
import com.farhanrv.worldnews.ui.home.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dbModule = module {
    factory { get<AppDatabase>().appDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "WorldNews.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val apiModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSrouce(get()) }
    single { LocalDataSource(get()) }
    factory { AppExecutors() }
    single { AppRepository(get(), get(), get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}

