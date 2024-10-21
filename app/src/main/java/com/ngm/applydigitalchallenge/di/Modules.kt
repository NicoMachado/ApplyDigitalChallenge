package com.ngm.applydigitalchallenge.di

import androidx.room.Room
import com.ngm.applydigitalchallenge.home.data.HackerNewsRepositoryImpl
import com.ngm.applydigitalchallenge.home.data.local.HackerNewsDao
import com.ngm.applydigitalchallenge.home.data.local.HackerNewsDatabase
import com.ngm.applydigitalchallenge.home.data.remote.HackerNewsApi
import com.ngm.applydigitalchallenge.home.domain.repository.HackerNewsRepository
import com.ngm.applydigitalchallenge.home.presentation.HomeScreenViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val DATABASE_NAME = "hackernews_db"

val appModule = module {
    single { provideRetrofit() }
    single { provideApiService(get()) }

    single {
        Room.databaseBuilder(
            androidContext(),
            HackerNewsDatabase::class.java,
            DATABASE_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    single<HackerNewsDao> { get<HackerNewsDatabase>().newsDao }

    single<HackerNewsRepository> { HackerNewsRepositoryImpl(get(), get()) }
    //single { RemoteDataSource(get()) }
    //single<CitiesRepository> { CitiesRepositoryImpl(get()) }

    viewModel { HomeScreenViewModel(get() ) }
    //viewModel { (handle: SavedStateHandle) -> MainViewModel(get(), handle) }
}

fun provideRetrofit(): Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl(HackerNewsApi.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideApiService(retrofit: Retrofit): HackerNewsApi {
    return retrofit.create(HackerNewsApi::class.java)
}