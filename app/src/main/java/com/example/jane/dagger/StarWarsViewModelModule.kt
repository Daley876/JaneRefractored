package com.example.jane.dagger

import com.example.jane.network.CharacterApiService
import com.example.jane.repository.StarWarsRepositoryImpl
import com.example.jane.utils.BASE_URL
import com.example.jane.viewmodel.StarWarsViewModel
import com.example.jane.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class StarWarsViewModelModule {

    @Singleton
    @Provides
    fun provideNetworkConnection(httpClient: OkHttpClient): CharacterApiService {

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(CharacterApiService::class.java)
        return retrofit
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        return client
    }


    @Singleton
    @Provides
    fun provideStarWarsRepository(apiService: CharacterApiService): StarWarsRepositoryImpl {
        return StarWarsRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideStarWarsViewModel(repo: StarWarsRepositoryImpl): StarWarsViewModel {
        return StarWarsViewModel(repo)
    }

    @Provides
    fun provideViewModelFactory(repo: StarWarsRepositoryImpl): ViewModelFactory {
        return ViewModelFactory(repo)
    }
}