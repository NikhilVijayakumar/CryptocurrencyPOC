package com.nikhil.cryptocurrency.di

import com.nikhil.cryptocurrency.commons.Constants
import com.nikhil.cryptocurrency.data.remote.CoinPaparikaApi
import com.nikhil.cryptocurrency.data.repo.CoinRepoImpl
import com.nikhil.cryptocurrency.domain.repo.CoinRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePaprikaAPI():CoinPaparikaApi = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CoinPaparikaApi::class.java)

    @Singleton
    @Provides
    fun provideCoinRepo(api: CoinPaparikaApi):CoinRepo = CoinRepoImpl(api)



}